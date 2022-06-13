package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.repository.ProdutoCategoriaRepository;
import br.com.businesstec.servicejet.client.dto.CategoriaDTO;
import br.com.businesstec.servicejet.service.CategoriaProdutoService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaProdutoServiceImpl implements CategoriaProdutoService {

    private final ProdutoCategoriaRepository produtoCategoriaRepository;

    public CategoriaProdutoServiceImpl(ProdutoCategoriaRepository produtoCategoriaRepository) {
        this.produtoCategoriaRepository = produtoCategoriaRepository;
    }

    @Override
    public List<CategoriaDTO> recuperarCategorias(Long produtoId) {
        var listaCategorias = produtoCategoriaRepository.findByIdProduto(produtoId).stream().map(x -> {
            var categoriaDto = new CategoriaDTO();
            categoriaDto.setExternalId(String.valueOf(x.getIdCategoria()));
            categoriaDto.setDefaultt(x.getPrincipal());
            return categoriaDto;
        }).collect(Collectors.toList());
        return listaCategorias.isEmpty() ? Collections.singletonList(getCategoriaDefault()) : listaCategorias;
    }

    private CategoriaDTO getCategoriaDefault() {
        var catDto = new CategoriaDTO();
        catDto.setExternalId("51"); // Categoria mais genérica possível, Cercamento Rurais
        catDto.setDefaultt(false);
        return catDto;
    }
}
