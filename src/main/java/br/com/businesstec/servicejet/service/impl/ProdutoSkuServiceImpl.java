package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ProdutoSku;
import br.com.businesstec.model.repository.ProdutoSkuRepository;
import br.com.businesstec.servicejet.service.ProdutoSkuService;
import org.springframework.stereotype.Service;

@Service
public class ProdutoSkuServiceImpl implements ProdutoSkuService {

    private final ProdutoSkuRepository produtoSkuRepository;

    public ProdutoSkuServiceImpl(ProdutoSkuRepository produtoSkuRepository) {
        this.produtoSkuRepository = produtoSkuRepository;
    }

    @Override
    public ProdutoSku recuperarSkuPorProdutoId(Long idProduto) {
        return produtoSkuRepository.findByIdProduto(idProduto).orElseThrow(() -> new RuntimeException("Não encontrada"));
    }

    @Override
    public ProdutoSku recuperarPorIdEntidade(Long idEntidade) {
        return produtoSkuRepository.findByIdEntidade(idEntidade).orElseThrow(() -> new RuntimeException("Não encontrado SKU por ID entidade"));
    }
}
