package br.com.businesstec.servicejet.service;

import br.com.businesstec.model.entities.VariacaoProdutoSku;

import java.util.List;

public interface VariacaoProdutoSkuService {

    List<VariacaoProdutoSku> getVariacaoProdutoSkuPorIdSku(Long externalIdSku);

}
