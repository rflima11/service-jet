package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.servicejet.client.PrecoProdutoJet;
import br.com.businesstec.servicejet.config.JetProperties;
import br.com.businesstec.servicejet.enums.EnumIntegracaoStrategy;
import br.com.businesstec.servicejet.service.IntegracaoStrategy;
import br.com.businesstec.servicejet.service.PrecoProdutoService;
import br.com.businesstec.servicejet.service.TokenService;
import org.springframework.stereotype.Service;

@Service
public class PrecoProdutoStrategy implements IntegracaoStrategy {

    private final PrecoProdutoService precoProdutoService;
    private final PrecoProdutoJet precoProdutoJet;
    private final JetProperties jetProperties;
    private final TokenService tokenService;

    public PrecoProdutoStrategy(PrecoProdutoService precoProdutoService, PrecoProdutoJet precoProdutoJet, JetProperties jetProperties, TokenService tokenService) {
        this.precoProdutoService = precoProdutoService;
        this.precoProdutoJet = precoProdutoJet;
        this.jetProperties = jetProperties;
        this.tokenService = tokenService;
    }

    @Override
    public void executar(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        var precoProduto = precoProdutoService.recuperarPeloIdEntidade(controleExecucaoFluxoEntidade.getIdEntidade());

        var accessToken = tokenService.getAccessToken(jetProperties.getProduto());

//        precoProdutoJet.atualizarPreco(accessToken, )

    }

    @Override
    public EnumIntegracaoStrategy getIntegracaoStrategy() {
        return null;
    }
}
