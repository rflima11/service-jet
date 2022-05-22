package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.servicejet.client.CategoriaJet;
import br.com.businesstec.servicejet.config.JetProperties;
import br.com.businesstec.servicejet.enums.EnumIntegracaoStrategy;
import br.com.businesstec.servicejet.mapper.CategoriaMapper;
import br.com.businesstec.servicejet.service.*;
import org.springframework.stereotype.Service;

@Service
public class CategoriaStrategy implements IntegracaoStrategy {

    private final CategoriaService categoriaService;
    private final CategoriaEcommerceService categoriaEcommerceService;
    private final TokenService tokenService;
    private final CategoriaJet categoriaJet;
    private final JetProperties jetProperties;
    private final CategoriaMapper categoriaMapper;
    private final ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService;

    public CategoriaStrategy(CategoriaService categoriaService, CategoriaEcommerceService categoriaEcommerceService, TokenService tokenService, CategoriaJet categoriaJet, JetProperties jetProperties, ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService) {
        this.categoriaService = categoriaService;
        this.categoriaEcommerceService = categoriaEcommerceService;
        this.tokenService = tokenService;
        this.categoriaJet = categoriaJet;
        this.jetProperties = jetProperties;
        this.controleExecucaoFluxoEntidadeService = controleExecucaoFluxoEntidadeService;
        categoriaMapper = CategoriaMapper.INSTANCE;
    }

    @Override
    public void executar(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        var categoriaModel = categoriaService.recuperarCategoriaNaoIntegradoByIdEntidade(controleExecucaoFluxoEntidade.getIdEntidade());
        var categoriaEcommerceModel = categoriaEcommerceService.encontrarCategoriaPeloIdCategoria(categoriaModel.getId());
        var categoriaDto = categoriaMapper.map(categoriaModel);
        categoriaDto.setActive(categoriaEcommerceModel.isAtivo());
        var accessToken = tokenService.getAccessToken(jetProperties.getProduto());

        var levelDto = categoriaJet.getLevelCategoria(accessToken, categoriaDto.getExternalId()).getBody();

        if (categoriaDto.getLevel().isBlank() && levelDto.getLevel().isBlank()) {
            categoriaJet.adicionarNovaCategoria(accessToken, categoriaDto);
        } else if (!categoriaDto.getLevel().isBlank()){
            var codigo = categoriaDto.getLevel().substring(0, 2);
            var categoriaPai = categoriaService.recuperarCategoriaPeloCodigo(codigo);
            var level = categoriaJet.getLevelCategoria(accessToken, categoriaPai.getIdentificadorOrigem()).getBody();
            categoriaDto.setLevel(level.getLevel());
            categoriaJet.adicionarNovaCategoria(accessToken, categoriaDto);
        }

        controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);

    }

    @Override
    public EnumIntegracaoStrategy getIntegracaoStrategy() {
        return EnumIntegracaoStrategy.CATEGORIA_STRATEGY;
    }
}
