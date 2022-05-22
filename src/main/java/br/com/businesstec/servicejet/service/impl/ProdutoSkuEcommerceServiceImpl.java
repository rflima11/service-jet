package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ProdutoSkuEcommerce;
import br.com.businesstec.model.repository.ProdutoSkuEcommerceRepository;
import br.com.businesstec.servicejet.service.ProdutoSkuEcommerceService;
import org.springframework.stereotype.Service;

@Service
public class ProdutoSkuEcommerceServiceImpl implements ProdutoSkuEcommerceService {

    private final ProdutoSkuEcommerceRepository repository;

    public ProdutoSkuEcommerceServiceImpl(ProdutoSkuEcommerceRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProdutoSkuEcommerce salvar(ProdutoSkuEcommerceService produtoSkuEcommerce) {
        return null;
    }

    @Override
    public ProdutoSkuEcommerce encontrarPeloIdProduto(Long idProduto) {
        return repository.findByIdProdutoSku(idProduto).orElseThrow(() -> new RuntimeException("Produto Sku Ecommerce não encontrado"));
    }
}
