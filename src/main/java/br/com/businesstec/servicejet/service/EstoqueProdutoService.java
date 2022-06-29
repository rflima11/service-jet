package br.com.businesstec.servicejet.service;

import br.com.businesstec.model.entities.EstoqueProduto;
import br.com.businesstec.model.entities.ImagemProduto;

public interface EstoqueProdutoService {

    EstoqueProduto recuperarPeloIdEntidade(Long idEntidade);

}
