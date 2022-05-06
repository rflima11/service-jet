package br.com.businesstec.servicejet.service;

import br.com.businesstec.model.entities.Variacao;
import br.com.businesstec.model.entities.VariacaoItem;
import br.com.businesstec.servicejet.client.dto.VariacaoDTO;

import java.util.List;

public interface VariacaoService {

    void integrarVariacoes(List<VariacaoDTO> variacoes);

    List<Variacao> recuperarVariacoes(String identificadorOrigem);


    VariacaoDTO getVariacaoRequest(Variacao variacao, VariacaoItem variacaoItem);
}
