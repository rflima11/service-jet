package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.servicejet.client.ProdutoJet;
import br.com.businesstec.servicejet.config.JetProperties;
import br.com.businesstec.servicejet.enums.EnumIntegracaoStrategy;
import br.com.businesstec.servicejet.mapper.EstoqueMapper;
import br.com.businesstec.servicejet.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EstoqueStrategyImpl  implements IntegracaoStrategy {

    private static final Logger logger = LoggerFactory.getLogger(EstoqueStrategyImpl.class);

    private final ProdutoJet produtoJet;
    private final JetProperties jetProperties;
    private final TokenService tokenService;
    private final EstoqueMapper mapper;
    private final EstoqueProdutoService estoqueProdutoService;
    private final ControleExecucaoFluxoEntidadeEntregaService controleExecucaoFluxoEntidadeEntregaService;
    private final ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService;

    private final ObjectMapper objectMapper;


    public EstoqueStrategyImpl(ProdutoJet produtoJet, JetProperties jetProperties, TokenService tokenService, EstoqueProdutoService estoqueProdutoService, ControleExecucaoFluxoEntidadeEntregaService controleExecucaoFluxoEntidadeEntregaService, ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService, ObjectMapper objectMapper) {
        this.produtoJet = produtoJet;
        this.jetProperties = jetProperties;
        this.tokenService = tokenService;
        this.estoqueProdutoService = estoqueProdutoService;
        this.controleExecucaoFluxoEntidadeEntregaService = controleExecucaoFluxoEntidadeEntregaService;
        this.controleExecucaoFluxoEntidadeService = controleExecucaoFluxoEntidadeService;
        this.objectMapper = objectMapper;
        mapper = EstoqueMapper.INSTANCE;
    }

    @Override
    @Retryable(
            value = RuntimeException.class,
            maxAttemptsExpression = "${config.retry.maxAttempts}",
            backoff = @Backoff(delayExpression = "${config.retry.maxDelay}")
    )
    public void executar(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {

        try {
            var estoqueProduto = estoqueProdutoService.recuperarPeloIdEntidade(controleExecucaoFluxoEntidade.getIdEntidade());
            var accessToken = tokenService.getAccessToken(jetProperties.getProduto());
            var request = mapper.map(estoqueProduto);
            var response = produtoJet.atualizarEstoqueProduto(accessToken, request).getBody();
            controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);
            controleExecucaoFluxoEntidadeEntregaService.atualizarExecucao(controleExecucaoFluxoEntidade, response, objectMapper.writeValueAsString(request));
            logger.info(String.format("ESTOQUE DO PRODUTO %s ATUALIZADO COM SUCESSO!", Objects.isNull(estoqueProduto.getIdentificadorOrigemProduto()) ? estoqueProduto.getIdentificadorOrigemSku() : estoqueProduto.getIdentificadorOrigemProduto() ));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Recover
    private void recover(RuntimeException e, ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);
        controleExecucaoFluxoEntidadeEntregaService.registrarErro(controleExecucaoFluxoEntidade, e.getMessage());
    }

    @Override
    public EnumIntegracaoStrategy getIntegracaoStrategy() {
        return EnumIntegracaoStrategy.PRODUTO_ESTOQUE;
    }
}
