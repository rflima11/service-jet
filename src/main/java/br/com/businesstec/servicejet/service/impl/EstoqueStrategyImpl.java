package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.servicejet.client.ProdutoJet;
import br.com.businesstec.servicejet.config.JetProperties;
import br.com.businesstec.servicejet.enums.EnumIntegracaoStrategy;
import br.com.businesstec.servicejet.mapper.EstoqueMapper;
import br.com.businesstec.servicejet.service.*;
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


    public EstoqueStrategyImpl(ProdutoJet produtoJet, JetProperties jetProperties, TokenService tokenService, EstoqueProdutoService estoqueProdutoService, ControleExecucaoFluxoEntidadeEntregaService controleExecucaoFluxoEntidadeEntregaService, ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService) {
        this.produtoJet = produtoJet;
        this.jetProperties = jetProperties;
        this.tokenService = tokenService;
        this.estoqueProdutoService = estoqueProdutoService;
        this.controleExecucaoFluxoEntidadeEntregaService = controleExecucaoFluxoEntidadeEntregaService;
        this.controleExecucaoFluxoEntidadeService = controleExecucaoFluxoEntidadeService;
        mapper = EstoqueMapper.INSTANCE;
    }

    @Override
    @Retryable(
            value = RuntimeException.class,
            maxAttemptsExpression = "${config.retry.maxAttempts}",
            backoff = @Backoff(delayExpression = "${config.retry.maxDelay}")
    )
    public void executar(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        var estoqueProduto = estoqueProdutoService.recuperarPeloIdEntidade(controleExecucaoFluxoEntidade.getIdEntidade());
        var accessToken = tokenService.getAccessToken(jetProperties.getProduto());
        var request = mapper.map(estoqueProduto);
        produtoJet.atualizarEstoqueProduto(accessToken, request);
        controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);
//        controleExecucaoFluxoEntidadeEntregaService.atualizarExecucao(controleExecucaoFluxoEntidade);
        logger.info(String.format("ESTOQUE DO PRODUTO %s ATUALIZADO COM SUCESSO!", Objects.isNull(estoqueProduto.getIdentificadorOrigemProduto()) ? estoqueProduto.getIdentificadorOrigemSku() : estoqueProduto.getIdentificadorOrigemProduto() ));
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
