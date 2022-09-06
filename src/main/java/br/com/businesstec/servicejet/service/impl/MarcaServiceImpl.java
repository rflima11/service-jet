package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.model.entities.Marca;
import br.com.businesstec.model.repository.MarcaRepository;
import br.com.businesstec.servicejet.client.MarcaClient;
import br.com.businesstec.servicejet.client.dto.MarcaDTO;
import br.com.businesstec.servicejet.client.dto.Queue;
import br.com.businesstec.servicejet.config.JetProperties;
import br.com.businesstec.servicejet.mapper.MarcaMapper;
import br.com.businesstec.servicejet.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MarcaServiceImpl implements MarcaService {

    private static final Logger logger = LoggerFactory.getLogger(MarcaServiceImpl.class);

    private static final String MENSAGEM_ERRO_MARCA_JA_CADASTRADA = "Erro [000] - Marca já cadastrada.";

    private final MarcaRepository marcaRepository;
    private final ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService;
    private final ControleExecucaoFluxoEntidadeEntregaService controleExecucaoFluxoEntidadeEntregaService;
    private final MarcaMapper marcaMapper;
    private final JetProperties jetProperties;
    private final TokenService tokenService;
    private final MarcaEcommerceService marcaEcommerceService;
    private final MarcaClient marcaClient;
    private final ObjectMapper objectMapper;

    public MarcaServiceImpl(MarcaRepository marcaRepository, ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService, ControleExecucaoFluxoEntidadeEntregaService controleExecucaoFluxoEntidadeEntregaService, JetProperties jetProperties, TokenService tokenService, MarcaEcommerceService marcaEcommerceService, MarcaClient marcaClient, ObjectMapper objectMapper) {
        this.marcaRepository = marcaRepository;
        this.controleExecucaoFluxoEntidadeService = controleExecucaoFluxoEntidadeService;
        this.controleExecucaoFluxoEntidadeEntregaService = controleExecucaoFluxoEntidadeEntregaService;
        this.jetProperties = jetProperties;
        this.tokenService = tokenService;
        this.marcaEcommerceService = marcaEcommerceService;
        this.marcaClient = marcaClient;
        this.objectMapper = objectMapper;
        marcaMapper = MarcaMapper.INSTANCE;
    }

    @Override
    public List<Marca> recuperarMarcasNaoIntegradas() {
        return new ArrayList<Marca>();
    }

    @Override
    public List<Marca> recuperarTodasMarcas() {
        return new ArrayList<Marca>();
    }

    @Override
    public Marca salvarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    @Override
    @Retryable(
            value = RuntimeException.class,
            maxAttemptsExpression = "${config.retry.maxAttempts}",
            backoff = @Backoff(delayExpression = "${config.retry.maxDelay}")
    )
    public void integrarMarcas(Marca marca, ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        try {
            logger.info("INICIANDO INTEGRAÇÃO MARCA " + marca.getDescricao());
            var marcaDto = marcaMapper.map(marca);
            var marcaEcommerce = marcaEcommerceService.encontrarPeloIdMarca(marca.getId());
            marcaDto.setActive(marcaEcommerce.getAtivo());
            var accessToken = tokenService.getAccessToken(jetProperties.getProduto());
            var marcaSalva = marcaClient.getIdentificadorMarca(accessToken, Collections.singletonList(Long.parseLong(marcaDto.getExternalId()))).getBody();
            logger.info("DETECTADO " + marcaSalva.size() + " JÁ CADASTRADA");
            String response = "";
            if (marcaSalva.isEmpty()) {
                response = marcaClient.inserirMarca(accessToken, marcaDto).getBody();
            } else {
                var marcaS = marcaSalva.get(0);
                logger.info("INICIANDO INTEGRAÇÃO MARCA " +  marca.getDescricao());
                marcaDto.setIdBrand(marcaS.getIdBrand());
                Thread.sleep(300);
                response = marcaClient.atualizarMarca(accessToken, marcaDto).getBody();
                logger.info("ATUALIZAÇÃO MARCA " +  marca.getDescricao() + " FEITA COM SUCESSO");
            }
            controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);
            controleExecucaoFluxoEntidadeEntregaService.atualizarExecucao(controleExecucaoFluxoEntidade, response, objectMapper.writeValueAsString(marcaDto));
            logger.info(String.format("MARCA %s INTEGRADA COM SUCESSO ", marcaDto.getName()));

        } catch (InterruptedException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private Optional<Long> verificaSeMarcaJaEstaCadastrada(List<Queue<MarcaDTO>> filaMarca, MarcaDTO marcaDTO) {
        var idBrands = filaMarca.stream().map(f -> {
            var marcaDtoFila = f.getEntity();
            return marcaDtoFila.getIdBrand();
        }).collect(Collectors.toList());

        return idBrands.stream().filter(i -> marcaDTO.getIdBrand() == i).findFirst();
    }

    @Recover
    private void recover(RuntimeException e, ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);
        controleExecucaoFluxoEntidadeEntregaService.registrarErro(controleExecucaoFluxoEntidade, e.getMessage());
    }

    @Override
    public Marca recuperarMarcaNaoIntegradoByIdEntidade(Long idEntidade) {
        return marcaRepository.findByIdEntidade(idEntidade).orElseThrow(() -> new RuntimeException("Não encontrada marca"));
    }


}
