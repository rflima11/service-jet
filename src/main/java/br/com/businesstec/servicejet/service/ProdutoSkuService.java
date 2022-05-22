package br.com.businesstec.servicejet.service;

import br.com.businesstec.model.entities.ProdutoSku;

public interface ProdutoSkuService {

    ProdutoSku recuperarSkuPorProdutoId(Long idProduto);

    ProdutoSku recuperarPorIdEntidade(Long idEntidade);

}
