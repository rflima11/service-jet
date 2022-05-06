package br.com.businesstec.servicejet.service;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.servicejet.enums.EnumIntegracaoStrategy;

public interface IntegracaoStrategy {

    void executar(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade);

    EnumIntegracaoStrategy getIntegracaoStrategy();
}
