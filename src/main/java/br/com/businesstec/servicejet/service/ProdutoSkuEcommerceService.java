package br.com.businesstec.servicejet.service;

import br.com.businesstec.model.entities.ProdutoSkuEcommerce;

public interface ProdutoSkuEcommerceService {

    ProdutoSkuEcommerce salvar(ProdutoSkuEcommerceService produtoSkuEcommerce);

    ProdutoSkuEcommerce encontrarPeloIdProduto(Long idProduto);
}
