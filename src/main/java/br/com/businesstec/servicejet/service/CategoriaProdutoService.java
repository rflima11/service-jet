package br.com.businesstec.servicejet.service;

import br.com.businesstec.servicejet.client.dto.CategoriaDTO;

import java.util.List;

public interface CategoriaProdutoService {

    List<CategoriaDTO> recuperarCategorias(Long produtoId);

}
