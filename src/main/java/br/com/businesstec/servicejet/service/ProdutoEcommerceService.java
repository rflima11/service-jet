package br.com.businesstec.servicejet.service;

import br.com.businesstec.model.entities.ProdutoEcommerce;

public interface ProdutoEcommerceService {

    ProdutoEcommerce findByIdProduto(Long idProduto);
}
