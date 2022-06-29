package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.model.entities.Variacao;
import br.com.businesstec.model.entities.VariacaoItem;
import br.com.businesstec.model.repository.VariacaoItemRepository;
import br.com.businesstec.model.repository.VariacaoRepository;
import br.com.businesstec.servicejet.client.VariacaoJet;
import br.com.businesstec.servicejet.client.dto.*;
import br.com.businesstec.servicejet.config.JetProperties;
import br.com.businesstec.servicejet.service.ControleExecucaoFluxoEntidadeEntregaService;
import br.com.businesstec.servicejet.service.ControleExecucaoFluxoEntidadeService;
import br.com.businesstec.servicejet.service.TokenService;
import br.com.businesstec.servicejet.service.VariacaoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class VariacaoServiceImpl implements VariacaoService {

    private static final Logger logger = LoggerFactory.getLogger(VariacaoServiceImpl.class);

    private static final String MENSAGEM_ERRO_VARIACAO_JA_CADASTRADA = "Referência já vinculada a esta Empresa!";
    private static final String MENSAGEM_ERRO_CODIGO_JA_CADASTRADO = "Código Externo já cadastrado para esta Empresa!";


    private final VariacaoRepository variacaoRepository;
    private final VariacaoItemRepository variacaoItemRepository;
    private final VariacaoJet variacaoJet;
    private final TokenService tokenService;
    private final JetProperties jetProperties;
    private final ObjectMapper objectMapper;
    private final ControleExecucaoFluxoEntidadeEntregaService controleExecucaoFluxoEntidadeEntregaService;
    private final ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService;

    public VariacaoServiceImpl(VariacaoRepository variacaoRepository, VariacaoItemRepository variacaoItemRepository, VariacaoJet variacaoJet, TokenService tokenService, JetProperties jetProperties, ObjectMapper objectMapper, ControleExecucaoFluxoEntidadeEntregaService controleExecucaoFluxoEntidadeEntregaService, ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService) {
        this.variacaoRepository = variacaoRepository;
        this.variacaoItemRepository = variacaoItemRepository;
        this.variacaoJet = variacaoJet;
        this.tokenService = tokenService;
        this.jetProperties = jetProperties;
        this.objectMapper = objectMapper;

        this.controleExecucaoFluxoEntidadeEntregaService = controleExecucaoFluxoEntidadeEntregaService;
        this.controleExecucaoFluxoEntidadeService = controleExecucaoFluxoEntidadeService;
    }

    @Override
    @Retryable(
            value = RuntimeException.class,
            maxAttemptsExpression = "${config.retry.maxAttempts}",
            backoff = @Backoff(delayExpression = "${config.retry.maxDelay}")
    )
    public void integrarVariacao(VariacaoDTO variacaoDto, ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {

        try {
            logger.info("INICIANDO INTEGRAÇÃO VARIAÇÃO " + variacaoDto.getName());

            logger.info("======= "  + " OBJETO ENVIADO: "+ " =======");
            logger.info(objectMapper.writeValueAsString(variacaoDto));
            logger.info("======================");

            var accessToken = tokenService.getAccessToken(jetProperties.getProduto());
            if (verificaSeVariacaoFoiCadastrada(variacaoDto, accessToken)) {
                atualizaVariacao(variacaoDto);
            } else {
                variacaoJet.adicionarNovaVariacao(accessToken, variacaoDto);
                logger.info("NOVA VARIAÇÃO ADICIONADA " + variacaoDto.getName());

            }
            controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);
            controleExecucaoFluxoEntidadeEntregaService.registrarExecucao(controleExecucaoFluxoEntidade);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void atualizaVariacao(VariacaoDTO variacaoDTO) {
        try {
            logger.info("VARIAÇÃO " + variacaoDTO.getName() + " JÁ INTEGRADA, INICIANDO ATUALIZAÇÃO");
            var accessToken = tokenService.getAccessToken(jetProperties.getProduto());

            Thread.sleep(300);
            variacaoJet.atualizarVariacao(accessToken, variacaoDTO);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean verificaSeVariacaoFoiCadastrada(VariacaoDTO variacaoDTO, String accessToken) {
        return variacaoJet.getVariacoes(accessToken).getBody().stream().anyMatch(f -> {
            var variacaoDtoFila = f.getEntity();
            return Objects.nonNull(variacaoDtoFila.getExternalId()) && variacaoDtoFila.getExternalId().equals(variacaoDTO.getExternalId()) || variacaoDtoFila.getName().equals(variacaoDTO.getName());
        });
    }


    @Override
    public VariacaoDTO getVariacaoRequest(Variacao variacao, VariacaoItem variacaoItem) {
        var variacaoDTO = new VariacaoDTO();
        var variacoesItemDTO = new VariationsDTO();
        variacoesItemDTO.setName(variacaoItem.getDescricao());
        variacoesItemDTO.setExternalId(variacaoItem.getIdentificadorOrigem());
        variacaoDTO.setName(variacao.getDescricao());
        variacaoDTO.setExternalId("2");
        variacaoDTO.setVariations(Collections.singletonList(variacoesItemDTO));
        return variacaoDTO;
    }

    @Override
    public Variacao recuperarVariacaoNaoIntegradasByIdEntidade(Long idEntidade) {
        return variacaoRepository.findByIdEntidade(idEntidade).orElseThrow(() -> new RuntimeException("Variação não encontrada"));
    }

    @Override
    public List<VariacaoItem> recuperarListaVariacoesByIdVariacao(Long idVariacao) {
        return variacaoItemRepository.findByIdVariacao(idVariacao);
    }

    @Override
    public List<VariacaoItem> getVariacaoItemByIdVariacaoItem(Long idVariacaoItem) {
        return variacaoItemRepository.findByIdVariacao(idVariacaoItem);
    }

    @Recover
    private void recover(RuntimeException e, ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);
        controleExecucaoFluxoEntidadeEntregaService.registrarErro(controleExecucaoFluxoEntidade, e.getMessage());
    }
}
