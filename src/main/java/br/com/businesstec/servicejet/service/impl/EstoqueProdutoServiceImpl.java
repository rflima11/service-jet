package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.EstoqueProduto;
import br.com.businesstec.model.repository.EstoqueProdutoRepository;
import br.com.businesstec.servicejet.service.EstoqueProdutoService;
import org.springframework.stereotype.Service;

@Service
public class EstoqueProdutoServiceImpl implements EstoqueProdutoService {

    private final EstoqueProdutoRepository repository;

    public EstoqueProdutoServiceImpl(EstoqueProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public EstoqueProduto recuperarPeloIdEntidade(Long idEntidade) {
        return repository.findByIdEntidade(idEntidade).orElseThrow(() -> new RuntimeException("Não encontrado ImagemProduto com o ID " + idEntidade));
    }
}
