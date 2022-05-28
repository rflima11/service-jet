package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.servicejet.client.ProdutoSkuJet;
import br.com.businesstec.servicejet.client.dto.ProdutoDTO;
import br.com.businesstec.servicejet.client.dto.ProdutoSkuDTO;
import br.com.businesstec.servicejet.client.dto.Queue;
import br.com.businesstec.servicejet.client.dto.VariationSkuDTO;
import br.com.businesstec.servicejet.config.JetProperties;
import br.com.businesstec.servicejet.enums.EnumIntegracaoStrategy;
import br.com.businesstec.servicejet.mapper.ProdutoSkuMapper;
import br.com.businesstec.servicejet.mapper.VariacaoMapper;
import br.com.businesstec.servicejet.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProdutoSkuStrategy implements IntegracaoStrategy {

    private static final String MENSAGEM_ERRO_PRODUTO_JA_CADASTRADO = "Houve um problema ao salvar o registro de produto, Código Externo informado, já cadastrado";
    private static final String MENSAGEM_ERRO_SKU_JA_CADASTRADO = "Houve um problema ao salvar o registro do SKU, Código Externo informado, já cadastrado.";

    private final JetProperties jetProperties;
    private final ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService;
    private final TokenService tokenService;
    private final ProdutoSkuJet produtoSkuJet;
    private final ProdutoSkuService produtoSkuService;
    private final ProdutoSkuEcommerceService produtoSkuEcommerceService;
    private final ProdutoSkuMapper mapper;
    private final VariacaoProdutoSkuService variacaoProdutoSkuService;
    private final VariacaoItemService variacaoItemService;
    private final ProdutoService produtoService;
    private final ObjectMapper objectMapper;
    private final VariacaoMapper variacaoMapper;


    public ProdutoSkuStrategy(JetProperties jetProperties, ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService, TokenService tokenService, ProdutoSkuJet produtoSkuJet, ProdutoSkuService produtoSkuService, ProdutoSkuEcommerceService produtoSkuEcommerceService, VariacaoProdutoSkuService variacaoProdutoSkuService, VariacaoItemService variacaoItemService, ProdutoService produtoService, ObjectMapper objectMapper) {
        this.jetProperties = jetProperties;
        this.controleExecucaoFluxoEntidadeService = controleExecucaoFluxoEntidadeService;
        this.tokenService = tokenService;
        this.produtoSkuJet = produtoSkuJet;
        this.produtoSkuService = produtoSkuService;
        this.produtoSkuEcommerceService = produtoSkuEcommerceService;
        this.variacaoProdutoSkuService = variacaoProdutoSkuService;
        this.variacaoItemService = variacaoItemService;
        this.produtoService = produtoService;
        this.objectMapper = objectMapper;
        variacaoMapper = VariacaoMapper.INSTANCE;
        mapper = ProdutoSkuMapper.INSTANCE;
    }

    @Override
    @Retryable(FeignException.class)
    public void executar(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        try {
            var produtoskuSalvo = produtoSkuService.recuperarPorIdEntidade(controleExecucaoFluxoEntidade.getIdEntidade());

            var accessToken = tokenService.getAccessToken(jetProperties.getProduto());
            var produtoSkuDto = mapper.map(produtoskuSalvo);
            var variacaoProdutoSku = variacaoProdutoSkuService.getVariacaoProdutoSkuPorIdSku(Long.valueOf(produtoskuSalvo.getIdentificadorOrigem()));

            var variacoesSku = variacaoProdutoSku.stream().map(varProdutoSku -> {
                var variacaoItem = variacaoItemService.getVariacaoItem(varProdutoSku.getIdVariacaoItem());
                var varSkuDto = new VariationSkuDTO();
                varSkuDto.setExternalId(variacaoItem.getIdentificadorOrigem());
                return varSkuDto;
            }).collect(Collectors.toList());

            var produtoSkuEcommerce = produtoSkuEcommerceService.encontrarPeloIdProduto(produtoskuSalvo.getId());
            produtoSkuDto.setActive(produtoSkuEcommerce.getAtivo());
            produtoSkuDto.setVisible(produtoSkuEcommerce.getVisivel());

            produtoSkuDto.setVariations(variacoesSku);
            System.out.println(produtoSkuDto);
            var idProduct = produtoService.recuperarIdProductByExternalId(produtoskuSalvo.getIdentificadorOrigemProduto(), accessToken);
            Thread.sleep(300);

            produtoSkuJet.adicionarNovoProdutoSku(accessToken, idProduct, produtoSkuDto);

            controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Recover
    private void recover(FeignException e) throws JsonProcessingException {
        if (e.contentUTF8().contains(MENSAGEM_ERRO_PRODUTO_JA_CADASTRADO) || e.contentUTF8().contains(MENSAGEM_ERRO_SKU_JA_CADASTRADO)) {
            var body = new String(e.request().body(), StandardCharsets.US_ASCII);
            var accessToken = tokenService.getAccessToken(jetProperties.getProduto());
            var produtoDto = objectMapper.readValue(body, ProdutoSkuDTO.class);
            var produtoSku = produtoSkuService.encontrarProdutoSkuPeloIdentificadorOrigem(produtoDto.getExternalId());
            var controleExecucaoFluxoEntidade = controleExecucaoFluxoEntidadeService.encontrarFluxoExecucaoEntidadeByIdEntidade(produtoSku.getIdEntidade());
            produtoSkuJet.atualizarProdutoSku(accessToken, 143L, objectMapper.readValue(body, ProdutoSkuDTO.class));
            if (Objects.nonNull(controleExecucaoFluxoEntidade)) {
                controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);
            }

        }
    }

    private void verificarSeProdutoSkuFoiIntegrado(List<Queue<ProdutoDTO>> produtos, ProdutoSkuDTO produtoSkuDTO, ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        var accessToken = tokenService.getAccessToken(jetProperties.getProduto());

        produtos.stream().forEach(x -> {
            var produto = x.getEntity();
            var produtosSku = produto.getSkUs();
            if (!produtosSku.isEmpty()) {
                var skuSalvo = produtosSku
                        .stream()
                        .filter(sku -> Objects.equals(sku.getExternalId(), produtoSkuDTO.getExternalId()))
                        .collect(Collectors.toList()).stream().findFirst();

                if (skuSalvo.isPresent()) {
                    produtoSkuJet.atualizarProdutoSku(accessToken, produto.getIdProduct(), produtoSkuDTO);
                    controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);
                }
            }
        });


//            return Objects.equals(produto.getExternalId(), produtoDTO.getExternalId());
//        }).findFirst();
//        if (optProdutoSalvo.isPresent()) {
//            produtoDTO.setIdProduct(optProdutoSalvo.get().getEntity().getIdProduct());
//            return true;
//        }
//        return false;


    }
    @Override
    public EnumIntegracaoStrategy getIntegracaoStrategy() {
        return EnumIntegracaoStrategy.PRODUTO_SKU_STRATEGY;
    }
}
