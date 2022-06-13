package br.com.businesstec.servicejet.service;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.model.entities.Variacao;
import br.com.businesstec.model.entities.VariacaoItem;
import br.com.businesstec.servicejet.client.dto.VariacaoDTO;

import java.util.List;

public interface VariacaoService {

    void integrarVariacao(VariacaoDTO variacao, ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade);

    VariacaoDTO getVariacaoRequest(Variacao variacao, VariacaoItem variacaoItem);

    Variacao recuperarVariacaoNaoIntegradasByIdEntidade(Long idEntidade);

    List<VariacaoItem> recuperarListaVariacoesByIdVariacao(Long idVariacao);

    List<VariacaoItem> getVariacaoItemByIdVariacaoItem(Long idVariacaoItem);

}
