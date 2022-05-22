package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.Produto;
import br.com.businesstec.model.repository.ProdutoRepository;
import br.com.businesstec.servicejet.client.ProdutoJet;
import br.com.businesstec.servicejet.service.ProdutoService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoJet produtoJet;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, ProdutoJet produtoJet) {
        this.produtoRepository = produtoRepository;
        this.produtoJet = produtoJet;
    }

    @Override
    public Produto recuperarProdutoNaoIntegradoByIdEntidade(Long idEntidade) {
        return produtoRepository.findByIdEntidade(idEntidade);
    }

    @Override
    public Produto encontrarProdutoPeloIdentificadorOrigem(String identificadorOrigem) {
        return produtoRepository.findByIdentificadorOrigem(identificadorOrigem).orElseThrow(() -> new RuntimeException("Produto não encontrado com o identificador origem " + identificadorOrigem));
    }

    @Override
    public Long recuperarIdProductByExternalId(String externalId, String accessToken) {
        var filaProduto = produtoJet.getFilaProduto(accessToken).getBody();

        var produto = filaProduto.stream().filter(entity -> {
            var produtoDTO = entity.getEntity();
            return Objects.equals(produtoDTO.getExternalId(), externalId);
        }).findFirst();

        return produto.orElseThrow(() -> new RuntimeException("Produto não encontrado na fila com o external ID: " + externalId)).getEntity().getIdProduct();
    }
}
