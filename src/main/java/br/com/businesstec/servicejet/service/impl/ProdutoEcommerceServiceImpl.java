package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ProdutoEcommerce;
import br.com.businesstec.model.repository.ProdutoEcommerceRepository;
import br.com.businesstec.servicejet.service.ProdutoEcommerceService;
import org.springframework.stereotype.Service;

@Service
public class ProdutoEcommerceServiceImpl implements ProdutoEcommerceService {

    private final ProdutoEcommerceRepository produtoEcommerceRepository;

    public ProdutoEcommerceServiceImpl(ProdutoEcommerceRepository produtoEcommerceRepository) {
        this.produtoEcommerceRepository = produtoEcommerceRepository;
    }

    @Override
    public ProdutoEcommerce findByIdProduto(Long idProduto) {
        return produtoEcommerceRepository.findByIdProduto(idProduto).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
}
