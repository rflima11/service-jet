package br.com.businesstec.servicejet.service;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;

import java.util.List;

public interface ControleExecucaoFluxoEntidadeService {

    List<ControleExecucaoFluxoEntidade> recuperarControlesFluxos();

    Long recuperarTipoEntidade(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade);

    ControleExecucaoFluxoEntidade atualizarIntegracao(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade);

    ControleExecucaoFluxoEntidade atualizarIntegracaoErro(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade);

    ControleExecucaoFluxoEntidade atualizarIntegracao(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade, Long idFila);

    ControleExecucaoFluxoEntidade registrar(Long idControleExecucaoFluxo, Long idEntidade);

    ControleExecucaoFluxoEntidade registrar(Long idControleExecucaoFluxo, Long idEntidade, Long idFila);

    ControleExecucaoFluxoEntidade encontrarFluxoExecucaoEntidadeByIdEntidade(Long idEntidade);
}
