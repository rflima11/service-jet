package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.servicejet.client.ProdutoSkuJet;
import br.com.businesstec.servicejet.client.dto.ProdutoDTO;
import br.com.businesstec.servicejet.client.dto.ProdutoSkuDTO;
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
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class ProdutoSkuStrategy implements IntegracaoStrategy {

    private static final String MENSAGEM_ERRO_PRODUTO_JA_CADASTRADO = "Houve um problema ao salvar o registro de produto, Código Externo informado, já cadastrado";

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
    public void executar(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        var produtoskuSalvo = produtoSkuService.recuperarPorIdEntidade(controleExecucaoFluxoEntidade.getIdEntidade());

        var accessToken = tokenService.getAccessToken(jetProperties.getProduto());
        var produtoSkuDto = mapper.map(produtoskuSalvo);
        var variacaoProdutoSku = variacaoProdutoSkuService.getVariacaoProdutoSkuPorIdSku(Long.valueOf(produtoskuSalvo.getIdentificadorOrigem()));
        var varItem = variacaoItemService.getVariacaoItem(variacaoProdutoSku.getIdVariacaoItem());
        var produtoSkuEcommerce = produtoSkuEcommerceService.encontrarPeloIdProduto(produtoskuSalvo.getId());

        produtoSkuDto.setActive(produtoSkuEcommerce.getAtivo());
        produtoSkuDto.setVisible(produtoSkuEcommerce.getVisivel());
        var varSkuDto = new VariationSkuDTO();
        varSkuDto.setExternalId(varItem.getIdentificadorOrigem());
        produtoSkuDto.adicionarVariacao(varSkuDto);
        System.out.println(produtoSkuDto);
        var idProduct = produtoService.recuperarIdProductByExternalId(produtoskuSalvo.getIdentificadorOrigemProduto(), accessToken);

        produtoSkuJet.adicionarNovoProdutoSku(accessToken, idProduct, produtoSkuDto);

        controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);
    }

    @Recover
    private void recover(FeignException e) throws JsonProcessingException {
        if (e.contentUTF8().contains(MENSAGEM_ERRO_PRODUTO_JA_CADASTRADO)) {
            var body = new String(e.request().body(), StandardCharsets.US_ASCII);
            var accessToken = tokenService.getAccessToken(jetProperties.getProduto());
            var produtoDto = objectMapper.readValue(body, ProdutoSkuDTO.class);
            var produto = produtoService.encontrarProdutoPeloIdentificadorOrigem(produtoDto.getExternalId());
            var controleExecucaoFluxoEntidade = controleExecucaoFluxoEntidadeService.encontrarFluxoExecucaoEntidadeByIdEntidade(produto.getIdEntidade());
            produtoJet.atualizarProduto(accessToken, objectMapper.readValue(body, ProdutoDTO.class));
            controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);

        }
    }


    @Override
    public EnumIntegracaoStrategy getIntegracaoStrategy() {
        return EnumIntegracaoStrategy.PRODUTO_SKU_STRATEGY;
    }
}
