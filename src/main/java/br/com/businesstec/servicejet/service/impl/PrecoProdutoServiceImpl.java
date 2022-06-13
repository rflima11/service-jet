package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.PrecoProduto;
import br.com.businesstec.model.repository.PrecoProdutoRepository;
import br.com.businesstec.servicejet.service.PrecoProdutoService;
import org.springframework.stereotype.Service;

@Service
public class PrecoProdutoServiceImpl implements PrecoProdutoService {

    private final PrecoProdutoRepository repository;

    public PrecoProdutoServiceImpl(PrecoProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public PrecoProduto recuperarPeloIdEntidade(Long idEntidade) {
        return repository.findByIdEntidade(idEntidade).orElseThrow(() -> new RuntimeException("Preço produto não encontrado com o ID entidade: " + idEntidade));
    }
}
