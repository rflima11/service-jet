package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.model.entities.Produto;
import br.com.businesstec.servicejet.client.ProdutoJet;
import br.com.businesstec.servicejet.client.dto.MarcaDTO;
import br.com.businesstec.servicejet.client.dto.ProdutoDTO;
import br.com.businesstec.servicejet.config.JetProperties;
import br.com.businesstec.servicejet.enums.EnumIntegracaoStrategy;
import br.com.businesstec.servicejet.mapper.ProdutoMapper;
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

@Service
public class ProdutoStrategyImpl implements IntegracaoStrategy {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoStrategyImpl.class);

    private final ProdutoService produtoService;
    private final ProdutoMapper produtoMapper;
    private final ProdutoEcommerceService produtoEcommerceService;
    private final ProdutoJet produtoJet;
    private final ObjectMapper objectMapper;
    private final JetProperties jetProperties;
    private final TokenService tokenService;
    private final ControleExecucaoFluxoEntidadeEntregaService execucaoFluxoEntidadeEntregaService;

    private final ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService;
    private final CategoriaProdutoService categoriaProdutoService;

    public ProdutoStrategyImpl(ProdutoService produtoService,
                               ProdutoEcommerceService produtoEcommerceService,
                               ProdutoJet produtoJet, ObjectMapper objectMapper, JetProperties jetProperties,
                               TokenService tokenService,
                               ControleExecucaoFluxoEntidadeEntregaService execucaoFluxoEntidadeEntregaService,
                               ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService,
                               CategoriaProdutoService categoriaProdutoService) {
        this.produtoService = produtoService;
        this.produtoEcommerceService = produtoEcommerceService;
        this.produtoJet = produtoJet;
        this.objectMapper = objectMapper;
        this.jetProperties = jetProperties;
        this.tokenService = tokenService;
        this.execucaoFluxoEntidadeEntregaService = execucaoFluxoEntidadeEntregaService;
        this.controleExecucaoFluxoEntidadeService = controleExecucaoFluxoEntidadeService;
        this.categoriaProdutoService = categoriaProdutoService;
        this.produtoMapper = ProdutoMapper.INSTANCE;
    }

    @Override
    @Retryable(
            value = RuntimeException.class,
            maxAttemptsExpression = "${config.retry.maxAttempts}",
            backoff = @Backoff(delayExpression = "${config.retry.maxDelay}")
    )
    public void executar(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        try {
            var produtoSalvo = produtoService.recuperarProdutoNaoIntegradoByIdEntidade(controleExecucaoFluxoEntidade.getIdEntidade());
            var produtoDto = formatarProduto(produtoSalvo);
            var accessToken = tokenService.getAccessToken(jetProperties.getProduto());

            logger.info("======= "  + " OBJETO ENVIADO: "+ " =======");
            logger.info(objectMapper.writeValueAsString(produtoDto));
            logger.info("======================");

            Thread.sleep(300);
            if (verificarSeProdutoFoiIntegrado(accessToken, produtoDto)) {
                logger.info(String.format("Produto %s já foi integrado, atualizando!", produtoDto.getName()));
                produtoJet.atualizarProduto(accessToken, produtoDto);
                Thread.sleep(300);
            } else {
                logger.info(String.format("Produto %s não foi integrado, integrando novo produto!", produtoDto.getName()));
                produtoJet.adicionarNovoProdutoJet(accessToken, produtoDto);
                Thread.sleep(300);
            }
            controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);
            execucaoFluxoEntidadeEntregaService.registrarExecucao(controleExecucaoFluxoEntidade);
            logger.info(String.format("Produto %s integrado com sucesso!", produtoDto.getName()));
        } catch (InterruptedException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private boolean verificarSeProdutoFoiIntegrado(String accessToken, ProdutoDTO produtoDTO) {
        try {
            Thread.sleep(300);
            var produtos = produtoJet.getFilaProduto(accessToken).getBody();
            var optProdutoSalvo = produtos.stream().filter(x -> {
                var produto = x.getEntity();
                return Objects.equals(produto.getExternalId(), produtoDTO.getExternalId());
            }).findFirst();
            if (optProdutoSalvo.isPresent()) {
                produtoDTO.setIdProduct(optProdutoSalvo.get().getEntity().getIdProduct());
                return true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    private ProdutoDTO formatarProduto(Produto produtoSalvo) {
        var produtoDto = produtoMapper.map(produtoSalvo);
        var produtoEcommerce = produtoEcommerceService.findByIdProduto(produtoSalvo.getId());
        produtoDto.setPromotionStore(produtoEcommerce.getPromocaoLoja());
        produtoDto.setCategories(categoriaProdutoService.recuperarCategorias(Long.valueOf(produtoDto.getExternalId())));
        produtoDto.setActive(produtoEcommerce.isAtivo());
        return produtoDto;
    }

    @Recover
    private void recover(RuntimeException e, ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);
        execucaoFluxoEntidadeEntregaService.registrarErro(controleExecucaoFluxoEntidade, e.getMessage());
    }

    @Override
    public EnumIntegracaoStrategy getIntegracaoStrategy() {
        return EnumIntegracaoStrategy.PRODUTO_STRATEGY;
    }

}
