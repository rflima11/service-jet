package br.com.businesstec.servicejet.service;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.model.entities.Produto;

import java.util.Optional;

public interface ProdutoService {

    Produto recuperarProdutoNaoIntegradoByIdEntidade(Long idEntidade);

    Produto encontrarProdutoPeloIdentificadorOrigem(String identificadorOrigem);

    Optional<Long> recuperarIdProductByExternalId(String externalId, String accessToken, ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade);
}
