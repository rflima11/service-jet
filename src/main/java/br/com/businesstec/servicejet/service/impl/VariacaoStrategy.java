package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.model.repository.VariacaoItemRepository;
import br.com.businesstec.servicejet.enums.EnumIntegracaoStrategy;
import br.com.businesstec.servicejet.service.ControleExecucaoFluxoEntidadeEntregaService;
import br.com.businesstec.servicejet.service.IntegracaoStrategy;
import br.com.businesstec.servicejet.service.VariacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class VariacaoStrategy implements IntegracaoStrategy {

    private static final Logger logger = LoggerFactory.getLogger(VariacaoStrategy.class);

    private final ControleExecucaoFluxoEntidadeEntregaService controleExecucaoFluxoEntidadeEntregaService;
    private final VariacaoService variacaoService;
    private final VariacaoItemRepository variacaoItemRepository;

    public VariacaoStrategy(ControleExecucaoFluxoEntidadeEntregaService controleExecucaoFluxoEntidadeEntregaService, VariacaoService variacaoService, VariacaoItemRepository variacaoItemRepository) {
        this.controleExecucaoFluxoEntidadeEntregaService = controleExecucaoFluxoEntidadeEntregaService;
        this.variacaoService = variacaoService;
        this.variacaoItemRepository = variacaoItemRepository;
    }

    @Override
    public void executar(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        var variacaos = variacaoService.recuperarVariacoes("1");
        var variacoesItem = variacaoItemRepository.findAll().get(0);
        logger.info("=============================================================================");
        logger.info("VARIACOES ENCONTRADAS: " + variacaos.size());
        logger.info("=============================================================================");

        variacaoService.integrarVariacoes(variacaos.stream().map(v -> variacaoService.getVariacaoRequest(v, variacoesItem)).collect(Collectors.toList()));

        logger.info("=============================================================================");
        logger.info("VARIACOES INTEGRADAS COM SUCESSO!!");
        logger.info("=============================================================================");

        controleExecucaoFluxoEntidadeEntregaService.registrarExecucao(controleExecucaoFluxoEntidade);

    }

    @Override
    public EnumIntegracaoStrategy getIntegracaoStrategy() {
        return EnumIntegracaoStrategy.VARIACAO_STRATEGY;
    }
}
