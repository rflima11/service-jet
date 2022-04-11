package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.servicejet.enums.EnumIntegracaoStrategy;
import br.com.businesstec.servicejet.model.ControleExecucaoFluxoEntidade;
import br.com.businesstec.servicejet.service.IntegracaoStrategy;
import org.springframework.stereotype.Service;

@Service
public class CategoriaStrategy implements IntegracaoStrategy {



    @Override
    public void executar(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {

    }

    @Override
    public EnumIntegracaoStrategy getIntegracaoStrategy() {
        return EnumIntegracaoStrategy.CATEGORIA_STRATEGY;
    }
}
