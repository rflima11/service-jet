package br.com.businesstec.servicejet.service;

import br.com.businesstec.model.entities.Categoria;
import br.com.businesstec.model.entities.Marca;

public interface CategoriaService {

    Categoria recuperarCategoriaNaoIntegradoByIdEntidade(Long idEntidade);

    Categoria recuperarCategoriaPeloCodigo(String codigo, Long idEntidade);

}
