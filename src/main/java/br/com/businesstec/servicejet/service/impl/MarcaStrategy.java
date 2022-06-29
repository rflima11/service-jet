package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.servicejet.enums.EnumIntegracaoStrategy;
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
        var marca = marcaService.recuperarMarcaNaoIntegradoByIdEntidade(controleExecucaoFluxoEntidade.getIdEntidade());


        marcaService.integrarMarcas(marca, controleExecucaoFluxoEntidade);
    }

    @Override
    public EnumIntegracaoStrategy getIntegracaoStrategy() {
        return EnumIntegracaoStrategy.MARCA_STRATEGY;
    }
}
