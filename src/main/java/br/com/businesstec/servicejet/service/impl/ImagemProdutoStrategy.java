package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.servicejet.client.ImagemProdutoJet;
import br.com.businesstec.servicejet.config.JetProperties;
import br.com.businesstec.servicejet.enums.EnumIntegracaoStrategy;
import br.com.businesstec.servicejet.mapper.ImagemProdutoMapper;
import br.com.businesstec.servicejet.service.ControleExecucaoFluxoEntidadeEntregaService;
import br.com.businesstec.servicejet.service.ImagemProdutoService;
import br.com.businesstec.servicejet.service.IntegracaoStrategy;
import br.com.businesstec.servicejet.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ImagemProdutoStrategy implements IntegracaoStrategy {

    private static final Logger logger = LoggerFactory.getLogger(ImagemProdutoStrategy.class);

    private final ImagemProdutoService imagemProdutoService;
    private final ControleExecucaoFluxoEntidadeEntregaService controleExecucaoFluxoEntidadeEntregaService;
    private final TokenService tokenService;
    private final JetProperties jetProperties;
    private final ImagemProdutoJet imagemProdutoJet;
    private final ImagemProdutoMapper mapper;

    public ImagemProdutoStrategy(ImagemProdutoService imagemProdutoService, ControleExecucaoFluxoEntidadeEntregaService controleExecucaoFluxoEntidadeEntregaService, TokenService tokenService, JetProperties jetProperties, ImagemProdutoJet imagemProdutoJet) {
        this.imagemProdutoService = imagemProdutoService;
        this.controleExecucaoFluxoEntidadeEntregaService = controleExecucaoFluxoEntidadeEntregaService;
        this.tokenService = tokenService;
        this.jetProperties = jetProperties;
        this.imagemProdutoJet = imagemProdutoJet;
        this.mapper = ImagemProdutoMapper.INSTANCE;
    }

    @Override
    public void executar(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        var produtoImagem = imagemProdutoService.recuperarPeloIdEntidade(controleExecucaoFluxoEntidade.getIdEntidade());
        var accessToken = tokenService.getAccessToken(jetProperties.getProduto());
        var produtoImgDTO = mapper.map(produtoImagem);
        produtoImgDTO.setImageName(produtoImgDTO.getImageName().concat(".jpg"));
        imagemProdutoJet.atualizarImagem(accessToken, produtoImgDTO);
//        controleExecucaoFluxoEntidadeEntregaService.atualizarExecucao(controleExecucaoFluxoEntidade);
        logger.info(String.format("IMAGEM PRODUTO EXTERNAL ID %s ATUALIZADA COM SUCESSO"));
    }

    @Override
    public EnumIntegracaoStrategy getIntegracaoStrategy() {
        return EnumIntegracaoStrategy.PRODUTO_IMAGEM;
    }
}
