package br.com.businesstec.servicejet.service;


import br.com.businesstec.model.entities.VariacaoItem;

import java.util.List;

public interface VariacaoItemService {

    VariacaoItem getVariacaoItemByIdVariacaoAndIdentificadorOrigem(Long idVariacao, String identificadorOrigem);
}
