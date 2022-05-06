package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.Produto;
import br.com.businesstec.model.repository.ProdutoRepository;
import br.com.businesstec.servicejet.service.ProdutoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto recuperarProdutoNaoIntegradoByIdEntidade(Long idEntidade) {
        return produtoRepository.findByIdEntidade(idEntidade);
    }
}
