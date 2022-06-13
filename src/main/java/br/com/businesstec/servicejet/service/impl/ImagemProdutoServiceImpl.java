package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ImagemProduto;
import br.com.businesstec.model.repository.ImagemProdutoRepository;
import br.com.businesstec.servicejet.service.ImagemProdutoService;
import org.springframework.stereotype.Service;

@Service
public class ImagemProdutoServiceImpl implements ImagemProdutoService {

    private final ImagemProdutoRepository repository;

    public ImagemProdutoServiceImpl(ImagemProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public ImagemProduto recuperarPeloIdEntidade(Long idEntidade) {
        return repository.findById(idEntidade).orElseThrow(() -> new RuntimeException("Imagem produto não encontrado com o ID " + idEntidade));
    }
}
