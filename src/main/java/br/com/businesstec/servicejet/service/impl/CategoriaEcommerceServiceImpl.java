package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.CategoriaEcommerce;
import br.com.businesstec.model.repository.CategoriaEcommerceRepository;
import br.com.businesstec.servicejet.service.CategoriaEcommerceService;
import org.springframework.stereotype.Service;

@Service
public class CategoriaEcommerceServiceImpl implements CategoriaEcommerceService {

    private final CategoriaEcommerceRepository categoriaEcommerceRepository;

    public CategoriaEcommerceServiceImpl(CategoriaEcommerceRepository categoriaEcommerceRepository) {
        this.categoriaEcommerceRepository = categoriaEcommerceRepository;
    }


    @Override
    public CategoriaEcommerce encontrarCategoriaPeloIdCategoria(Long idCategoria) {
        return categoriaEcommerceRepository.findByIdCategoria(idCategoria).orElseThrow(() -> new RuntimeException("Categoria Ecommerce não encontrada"));
    }
}
