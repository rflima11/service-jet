package br.com.businesstec.servicejet.service;

import br.com.businesstec.servicejet.client.dto.VariacaoDTO;
import br.com.businesstec.servicejet.model.Variacao;
import br.com.businesstec.servicejet.model.VariacaoItem;

import java.util.List;

public interface VariacaoService {

    void integrarVariacoes(List<VariacaoDTO> variacoes);

    List<Variacao> recuperarVariacoes(String identificadorOrigem);


    VariacaoDTO getVariacaoRequest(Variacao variacao, VariacaoItem variacaoItem);
}
