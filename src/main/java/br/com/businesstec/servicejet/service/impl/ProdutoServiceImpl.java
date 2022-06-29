package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.model.entities.Produto;
import br.com.businesstec.model.repository.ProdutoRepository;
import br.com.businesstec.servicejet.client.ProdutoJet;
import br.com.businesstec.servicejet.service.ControleExecucaoFluxoEntidadeEntregaService;
import br.com.businesstec.servicejet.service.ProdutoService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ControleExecucaoFluxoEntidadeEntregaService controleExecucaoFluxoEntidadeEntregaService;
    private final ProdutoJet produtoJet;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, ControleExecucaoFluxoEntidadeEntregaService controleExecucaoFluxoEntidadeEntregaService, ProdutoJet produtoJet) {
        this.produtoRepository = produtoRepository;
        this.controleExecucaoFluxoEntidadeEntregaService = controleExecucaoFluxoEntidadeEntregaService;
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
    public Optional<Long> recuperarIdProductByExternalId(String externalId, String accessToken, ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        var filaProduto = produtoJet.getFilaProduto(accessToken).getBody();

        var produto = filaProduto.stream().filter(entity -> {
            var produtoDTO = entity.getEntity();
            return Objects.equals(produtoDTO.getExternalId(), externalId);
        }).findFirst();

        if (produto.isPresent()) {
            return Optional.of(produto.get().getEntity().getIdProduct());
        } else {
            return Optional.empty();
        }

    }
}
