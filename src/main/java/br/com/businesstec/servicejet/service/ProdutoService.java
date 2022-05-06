package br.com.businesstec.servicejet.service;

import br.com.businesstec.model.entities.Produto;

import java.util.List;

public interface ProdutoService {

    Produto recuperarProdutoNaoIntegradoByIdEntidade(Long idEntidade);
}
