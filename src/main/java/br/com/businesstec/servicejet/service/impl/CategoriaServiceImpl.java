package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.Categoria;
import br.com.businesstec.model.repository.CategoriaRepository;
import br.com.businesstec.servicejet.service.CategoriaService;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria recuperarCategoriaNaoIntegradoByIdEntidade(Long idEntidade) {
        return categoriaRepository.findByIdEntidade(idEntidade).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    @Override
    public Categoria recuperarCategoriaPeloCodigo(String codigo) {
        return categoriaRepository.findByCodigo(codigo).orElseThrow(() -> new RuntimeException("Categoria não encontrada com o código" + codigo));
    }
}
