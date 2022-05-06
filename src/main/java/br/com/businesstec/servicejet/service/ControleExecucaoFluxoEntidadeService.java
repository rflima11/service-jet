package br.com.businesstec.servicejet.service;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;

import java.util.List;

public interface ControleExecucaoFluxoEntidadeService {

    List<ControleExecucaoFluxoEntidade> recuperarControlesFluxos();

    Long recuperarTipoEntidade(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade);

    ControleExecucaoFluxoEntidade atualizarIntegracao(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade);

    ControleExecucaoFluxoEntidade registrar(Long idControleExecucaoFluxo, Long idEntidade);

    ControleExecucaoFluxoEntidade encontrarFluxoExecucaoEntidadeByIdEntidade(Long idEntidade);
}
