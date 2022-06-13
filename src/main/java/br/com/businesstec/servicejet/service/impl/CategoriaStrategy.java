package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.Categoria;
import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.servicejet.client.CategoriaJet;
import br.com.businesstec.servicejet.client.dto.CategoriaDTO;
import br.com.businesstec.servicejet.client.dto.VariacaoDTO;
import br.com.businesstec.servicejet.config.JetProperties;
import br.com.businesstec.servicejet.enums.EnumIntegracaoStrategy;
import br.com.businesstec.servicejet.mapper.CategoriaMapper;
import br.com.businesstec.servicejet.service.*;
import br.com.businesstec.servicejet.utils.LogConstants;
import br.com.businesstec.servicejet.utils.LogUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Service
public class CategoriaStrategy implements IntegracaoStrategy {

    private static final Logger logger = LoggerFactory.getLogger(CategoriaStrategy.class);

    private final CategoriaService categoriaService;
    private final CategoriaEcommerceService categoriaEcommerceService;
    private final TokenService tokenService;
    private final CategoriaJet categoriaJet;
    private final ObjectMapper objectMapper;
    private final JetProperties jetProperties;
    private final CategoriaMapper categoriaMapper;
    private final ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService;
    private final ControleExecucaoFluxoEntidadeEntregaService controleExecucaoFluxoEntidadeEntregaService;

    public CategoriaStrategy(CategoriaService categoriaService, CategoriaEcommerceService categoriaEcommerceService, TokenService tokenService, CategoriaJet categoriaJet, ObjectMapper objectMapper, JetProperties jetProperties, ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService, ControleExecucaoFluxoEntidadeEntregaService controleExecucaoFluxoEntidadeEntregaService) {
        this.categoriaService = categoriaService;
        this.categoriaEcommerceService = categoriaEcommerceService;
        this.tokenService = tokenService;
        this.categoriaJet = categoriaJet;
        this.objectMapper = objectMapper;
        this.jetProperties = jetProperties;
        this.controleExecucaoFluxoEntidadeService = controleExecucaoFluxoEntidadeService;
        this.controleExecucaoFluxoEntidadeEntregaService = controleExecucaoFluxoEntidadeEntregaService;
        categoriaMapper = CategoriaMapper.INSTANCE;
    }

    @Override
    @Retryable(
            value = RuntimeException.class,
            maxAttemptsExpression = "${config.retry.maxAttempts}",
            backoff = @Backoff(delayExpression = "${config.retry.maxDelay}")
    )
    public void executar(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        try {
            var categoriaModel = categoriaService.recuperarCategoriaNaoIntegradoByIdEntidade(controleExecucaoFluxoEntidade.getIdEntidade());
            var categoriaDto = formatarCategoria(categoriaModel);
            var accessToken = tokenService.getAccessToken(jetProperties.getProduto());
            Thread.sleep(300);
            var levelDto = categoriaJet.getLevelCategoria(accessToken, categoriaDto.getExternalId()).getBody();
            LogUtils.defaultInfoLog(logger, LogConstants.INTEGRAR_NOVA_CATEGORIA);

            logger.info("======= "  + " OBJETO ENVIADO: "+ " =======");
            logger.info(objectMapper.writeValueAsString(categoriaDto));
            logger.info("======================");


            if (categoriaDto.getLevel().isBlank() && levelDto.getLevel().isBlank()) {
                Thread.sleep(300);
                categoriaJet.adicionarNovaCategoria(accessToken, categoriaDto);
                LogUtils.defaultInfoLog(logger, LogConstants.CATEGORIA_INTEGRADA_SUCESSO);
            } else if (!categoriaDto.getLevel().isBlank() && !isCategoriaIntegrada(categoriaDto.getExternalId(), accessToken)){
                Thread.sleep(300);
                ajustarLevelCategoria(categoriaDto, categoriaModel, accessToken);
                Thread.sleep(300);
                categoriaJet.adicionarNovaCategoria(accessToken, categoriaDto);
                LogUtils.defaultInfoLog(logger, LogConstants.CATEGORIA_FILHA_ITNEGRADA_SUCESSO);

            } else if (isCategoriaIntegrada(categoriaDto.getExternalId(), accessToken)) {
                Thread.sleep(300);
                categoriaJet.atualizarCategoria(accessToken, categoriaDto);
                LogUtils.defaultInfoLog(logger, LogConstants.CATEGORIA_ATUALIZADA_SUCESSO);

            }
            controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);
            controleExecucaoFluxoEntidadeEntregaService.registrarExecucao(controleExecucaoFluxoEntidade);
        } catch (InterruptedException | JsonProcessingException e) {
            e.printStackTrace();
        }

    }


    public boolean isCategoriaIntegrada(String externalId, String accessToken) {
        var categoriaExists = false;
        try {
            Thread.sleep(300);
            var filaCategoria = categoriaJet.getCategorias(accessToken).getBody();

            var optCategoria = filaCategoria.stream().filter(entity -> {
                var externalIdQueue = entity.getEntity().getExternalId();
                return Objects.equals(externalIdQueue, externalId);
            }).findFirst();

            categoriaExists = optCategoria.isPresent();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return categoriaExists;
    }

    private CategoriaDTO formatarCategoria(Categoria categoriaModel) {
        var categoriaEcommerceModel = categoriaEcommerceService.encontrarCategoriaPeloIdCategoria(categoriaModel.getId());
        var categoriaDto = categoriaMapper.map(categoriaModel);
        categoriaDto.setActive(categoriaEcommerceModel.isAtivo());
        return categoriaDto;
    }

    private void ajustarLevelCategoria(CategoriaDTO categoriaDTO, Categoria categoriaModel, String accessToken) {
        var codigo = categoriaDTO.getLevel().substring(0, 2);
        var categoriaPai = categoriaService.recuperarCategoriaPeloCodigo(codigo, categoriaModel.getIdEntidade());
        var level = categoriaJet.getLevelCategoria(accessToken, categoriaPai.getIdentificadorOrigem()).getBody();
        categoriaDTO.setLevel(level.getLevel());
    }

    @Recover
    private void recover(RuntimeException e, ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);
        controleExecucaoFluxoEntidadeEntregaService.registrarErro(controleExecucaoFluxoEntidade, e.getMessage());
    }



    @Override
    public EnumIntegracaoStrategy getIntegracaoStrategy() {
        return EnumIntegracaoStrategy.CATEGORIA_STRATEGY;
    }
}
