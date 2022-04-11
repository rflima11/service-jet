package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.servicejet.enums.EnumIntegracaoStrategy;
import br.com.businesstec.servicejet.model.ControleExecucaoFluxoEntidade;
import br.com.businesstec.servicejet.service.ControleExecucaoFluxoEntidadeEntregaService;
import br.com.businesstec.servicejet.service.IntegracaoStrategy;
import br.com.businesstec.servicejet.service.MarcaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MarcaStrategy implements IntegracaoStrategy {

    private static final Logger logger = LoggerFactory.getLogger(MarcaStrategy.class);

    private final MarcaService marcaService;
    private final ControleExecucaoFluxoEntidadeEntregaService controleExecucaoFluxoEntidadeEntregaService;

    public MarcaStrategy(MarcaService marcaService, ControleExecucaoFluxoEntidadeEntregaService controleExecucaoFluxoEntidadeEntregaService) {
        this.marcaService = marcaService;
        this.controleExecucaoFluxoEntidadeEntregaService = controleExecucaoFluxoEntidadeEntregaService;
    }

    @Override
    public void executar(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        var marcas = marcaService.recuperarMarcasNaoIntegradas();

        logger.info("=============================================================================");
        logger.info("MARCAS ENCONTRADAS: " + marcas.size());
        logger.info("=============================================================================");

        marcaService.integrarMarcas(marcas, controleExecucaoFluxoEntidade);
    }

    @Override
    public EnumIntegracaoStrategy getIntegracaoStrategy() {
        return EnumIntegracaoStrategy.MARCA_STRATEGY;
    }
}
