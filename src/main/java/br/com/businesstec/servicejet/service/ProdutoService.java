package br.com.businesstec.servicejet.service;

import br.com.businesstec.model.entities.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    Produto recuperarProdutoNaoIntegradoByIdEntidade(Long idEntidade);

    Produto encontrarProdutoPeloIdentificadorOrigem(String identificadorOrigem);

    Long recuperarIdProductByExternalId(String externalId, String accessToken);
}
