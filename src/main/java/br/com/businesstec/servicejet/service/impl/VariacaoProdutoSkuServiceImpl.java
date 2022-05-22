package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.VariacaoProdutoSku;
import br.com.businesstec.model.repository.VariacaoProdutoSkuRepository;
import br.com.businesstec.servicejet.service.VariacaoProdutoSkuService;
import org.springframework.stereotype.Service;

@Service
public class VariacaoProdutoSkuServiceImpl implements VariacaoProdutoSkuService {

    private final VariacaoProdutoSkuRepository repository;

    public VariacaoProdutoSkuServiceImpl(VariacaoProdutoSkuRepository repository) {
        this.repository = repository;
    }

    @Override
    public VariacaoProdutoSku getVariacaoProdutoSkuPorIdSku(Long externalIdSku) {
        return repository.findByIdProdutoSku(externalIdSku).orElseThrow(() -> new RuntimeException("Não encontrada variação produto sku " + externalIdSku));
    }
}
