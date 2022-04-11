package br.com.businesstec.servicejet.service;

import br.com.businesstec.servicejet.model.ControleExecucaoFluxoEntidade;

import java.util.List;

public interface ControleExecucaoFluxoEntidadeService {

    List<ControleExecucaoFluxoEntidade> recuperarControlesFluxos();

    Long recuperarTipoEntidade(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade);

    ControleExecucaoFluxoEntidade atualizarIntegracao(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade);
}
