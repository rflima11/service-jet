package br.com.businesstec.servicejet.service;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidadeEntrega;

public interface ControleExecucaoFluxoEntidadeEntregaService {

    ControleExecucaoFluxoEntidadeEntrega atualizarExecucao(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade);

    ControleExecucaoFluxoEntidadeEntrega registrarErro(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade, String descricaoErro);

    ControleExecucaoFluxoEntidadeEntrega encontrarPeloIdControleExecucaoFluxo(Long idControleExecucaoFluxo);
}
