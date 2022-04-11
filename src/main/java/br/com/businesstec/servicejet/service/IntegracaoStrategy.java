package br.com.businesstec.servicejet.service;

import br.com.businesstec.servicejet.enums.EnumIntegracaoStrategy;
import br.com.businesstec.servicejet.model.ControleExecucaoFluxoEntidade;

public interface IntegracaoStrategy {

    void executar(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade);

    EnumIntegracaoStrategy getIntegracaoStrategy();
}
