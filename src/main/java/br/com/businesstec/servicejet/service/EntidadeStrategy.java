package br.com.businesstec.servicejet.service;

import br.com.businesstec.model.entities.ControleExecucaoFluxo;
import br.com.businesstec.servicejet.enums.EnumEntidadeStrategy;

public interface EntidadeStrategy {

    void executar(ControleExecucaoFluxo controleExecucaoFluxo);

    EnumEntidadeStrategy getEntidadeStrategy();
}
