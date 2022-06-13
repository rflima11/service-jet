package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.model.entities.ProdutoSku;
import br.com.businesstec.servicejet.client.ProdutoJet;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProdutoSkuStrategy implements IntegracaoStrategy {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoSkuStrategy.class);

    private final JetProperties jetProperties;
    private final ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService;
    private final ControleExecucaoFluxoEntidadeEntregaService execucaoFluxoEntidadeEntregaService;
    private final TokenService tokenService;
    private final ProdutoSkuJet produtoSkuJet;
    private final ProdutoSkuService produtoSkuService;
    private final ProdutoSkuEcommerceService produtoSkuEcommerceService;
    private final ProdutoSkuMapper mapper;
    private final ProdutoJet produtoJet;
    private final VariacaoProdutoSkuService variacaoProdutoSkuService;
    private final ProdutoService produtoService;
    private final ObjectMapper objectMapper;


    public ProdutoSkuStrategy(JetProperties jetProperties,
                              ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService,
                              ControleExecucaoFluxoEntidadeEntregaService execucaoFluxoEntidadeEntregaService,
                              TokenService tokenService,
                              ProdutoSkuJet produtoSkuJet,
                              ProdutoSkuService produtoSkuService,
                              ProdutoSkuEcommerceService produtoSkuEcommerceService,
                              ProdutoJet produtoJet,
                              VariacaoProdutoSkuService variacaoProdutoSkuService,
                              ProdutoService produtoService,
                              ObjectMapper objectMapper) {
        this.jetProperties = jetProperties;
        this.controleExecucaoFluxoEntidadeService = controleExecucaoFluxoEntidadeService;
        this.execucaoFluxoEntidadeEntregaService = execucaoFluxoEntidadeEntregaService;
        this.tokenService = tokenService;
        this.produtoSkuJet = produtoSkuJet;
        this.produtoSkuService = produtoSkuService;
        this.produtoSkuEcommerceService = produtoSkuEcommerceService;
        this.produtoJet = produtoJet;
        this.variacaoProdutoSkuService = variacaoProdutoSkuService;
        this.produtoService = produtoService;
        this.objectMapper = objectMapper;
        mapper = ProdutoSkuMapper.INSTANCE;
    }

    @Override
    @Retryable(
            value = RuntimeException.class,
            maxAttemptsExpression = "${config.retry.maxAttempts}",
            backoff = @Backoff(delayExpression = "${config.retry.maxDelay}")
    )
    public void executar(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        try {
            var produtoskuSalvo = produtoSkuService.recuperarPorIdEntidade(controleExecucaoFluxoEntidade.getIdEntidade());
            var accessToken = tokenService.getAccessToken(jetProperties.getProduto());
            var produtoSkuDto = formatarProdutoSku(produtoskuSalvo);
            Thread.sleep(300);
            var idProduct = produtoService.recuperarIdProductByExternalId(produtoskuSalvo.getIdentificadorOrigemProduto(), accessToken, controleExecucaoFluxoEntidade);

            logger.info("======= "  + " OBJETO ENVIADO: "+ " =======");
            logger.info(objectMapper.writeValueAsString(produtoSkuDto));
            logger.info("======================");

            if (idProduct.isPresent()) {
                adicionarVariacoes(produtoskuSalvo, produtoSkuDto);
                var idProdutoFila = idProduct.get();
                if (!verificarSeProdutoSkuFoiIntegrado(produtoSkuDto)) {
                    logger.info(String.format("Novo Sku Code %s realizando inserção", produtoSkuDto.getSkuCode()));
                    Thread.sleep(300);
                    produtoSkuJet.adicionarNovoProdutoSku(accessToken, idProdutoFila, produtoSkuDto);
                } else {
                    logger.info(String.format("Sku Code %s já foi integrado, atualizando!", produtoSkuDto.getSkuCode()));
                    Thread.sleep(300);
                    produtoSkuJet.atualizarProdutoSku(accessToken, idProdutoFila, produtoSkuDto);
                }
                controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);
                execucaoFluxoEntidadeEntregaService.registrarExecucao(controleExecucaoFluxoEntidade);
                logger.info(String.format("Sku Code %s integrado com sucesso!", produtoSkuDto.getSkuCode()));
            } else {
                logger.info(String.format("Produto com externalId %s não encontrado na fila", produtoskuSalvo.getIdentificadorOrigemProduto()));
                execucaoFluxoEntidadeEntregaService.registrarErro(controleExecucaoFluxoEntidade, String.format("Produto com externalId %s não encontrado na fila", produtoskuSalvo.getIdentificadorOrigemProduto()));
            }

        } catch (InterruptedException | JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    private boolean verificarSeProdutoSkuFoiIntegrado(ProdutoSkuDTO produtoSkuDTO) {
        var accessToken = tokenService.getAccessToken(jetProperties.getProduto());
        return produtoJet.getFilaProduto(accessToken).getBody().stream().anyMatch(x -> {
            var produto = x.getEntity();
            var produtosSku = produto.getSkUs();
            return produtosSku.stream().anyMatch(sku -> Objects.equals(sku.getExternalId(), produtoSkuDTO.getExternalId()));
        });
    }

    private ProdutoSkuDTO formatarProdutoSku(ProdutoSku produtoSkuSalvo) {
        var produtoSkuEcommerce = produtoSkuEcommerceService.encontrarPeloIdProduto(produtoSkuSalvo.getId());
        var produtoSkuDto = mapper.map(produtoSkuSalvo);
        produtoSkuDto.setActive(produtoSkuEcommerce.getAtivo());
        produtoSkuDto.setVisible(produtoSkuEcommerce.getVisivel());
        return produtoSkuDto;
    }

    private void adicionarVariacoes(ProdutoSku produtoSkuSalvo, ProdutoSkuDTO produtoSkuDTO) {
        var variacoesProdutoSku = variacaoProdutoSkuService.getVariacaoProdutoSkuPorIdSku(Long.valueOf(produtoSkuDTO.getExternalId()));
        var variationsSkuDto = variacoesProdutoSku.stream().map(x -> new VariationSkuDTO(x.getIdVariacaoItem())).collect(Collectors.toList());
        produtoSkuDTO.setVariations(variationsSkuDto);
    }

    @Recover
    private void recover(RuntimeException e, ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);
        execucaoFluxoEntidadeEntregaService.registrarErro(controleExecucaoFluxoEntidade, e.getMessage());
    }


    @Override
    public EnumIntegracaoStrategy getIntegracaoStrategy() {
        return EnumIntegracaoStrategy.PRODUTO_SKU_STRATEGY;
    }
}
