package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.model.entities.Marca;
import br.com.businesstec.model.repository.MarcaRepository;
import br.com.businesstec.servicejet.client.MarcaClient;
import br.com.businesstec.servicejet.client.dto.MarcaDTO;
import br.com.businesstec.servicejet.config.JetProperties;
import br.com.businesstec.servicejet.mapper.MarcaMapper;
import br.com.businesstec.servicejet.service.ControleExecucaoFluxoEntidadeService;
import br.com.businesstec.servicejet.service.MarcaService;
import br.com.businesstec.servicejet.service.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarcaServiceImpl implements MarcaService {

    private static final Logger logger = LoggerFactory.getLogger(MarcaServiceImpl.class);

    private static final String MENSAGEM_ERRO_MARCA_JA_CADASTRADA = "Erro [000] - Marca já cadastrada.";

    private final MarcaRepository marcaRepository;
    private final ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService;
    private final MarcaMapper marcaMapper;
    private final JetProperties jetProperties;
    private final TokenService tokenService;
    private final MarcaClient marcaClient;
    private final ObjectMapper objectMapper;

    public MarcaServiceImpl(MarcaRepository marcaRepository, ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService, JetProperties jetProperties, TokenService tokenService, MarcaClient marcaClient, ObjectMapper objectMapper) {
        this.marcaRepository = marcaRepository;
        this.controleExecucaoFluxoEntidadeService = controleExecucaoFluxoEntidadeService;
        this.jetProperties = jetProperties;
        this.tokenService = tokenService;
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
    @Retryable(FeignException.class)
    public void integrarMarcas(Marca marca, ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        var marcaDto = marcaMapper.map(marca);
        var accessToken = tokenService.getAccessToken(jetProperties.getProduto());
        marcaClient.inserirMarca(accessToken, marcaDto);

        controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);


//        marcas.stream().forEach(marca -> {
//            try {
//                var idMarcaJet = jetMarca.integrarMarca(marcaMapper.map(marca));
//                if (Objects.isNull(idMarcaJet)) {
////                    throw new RuntimeException("Não foi possível integrar marca, id: " + marca.getIdMarca());
//                }
////                marca.setIdMarcaJet(Long.parseLong(idMarcaJet));
//                var marcaSalva = salvarMarca(marca);
//                logger.info("=============================================================================");
////                logger.info("MARCA INTEGRADA COM SUCESSO, ID JET:  " + marcaSalva.getIdMarcaJet());
//                logger.info("=============================================================================");
//                Thread.sleep(250);
//                controleExecucaoFluxoEntidadeEntregaService.registrarExecucao(controleExecucaoFluxoEntidade);
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
    }

    @Recover
    private void recover(FeignException e) throws JsonProcessingException {
        if (e.contentUTF8().contains(MENSAGEM_ERRO_MARCA_JA_CADASTRADA)) {
            var body = new String(e.request().body(), StandardCharsets.US_ASCII);
            var marcaDto = objectMapper.readValue(body, MarcaDTO.class);
            var marca = marcaRepository.findByIdentificadorOrigem(marcaDto.getExternalId()).orElseThrow(() -> new RuntimeException("Marca não encontrada com o ID " + marcaDto.getExternalId()));
            var controleExecucaoFluxoEntidade = controleExecucaoFluxoEntidadeService.encontrarFluxoExecucaoEntidadeByIdEntidade(marca.getIdEntidade());
            var accessToken = tokenService.getAccessToken(jetProperties.getProduto());
            marcaClient.atualizarMarca(accessToken, objectMapper.readValue(body, MarcaDTO.class));
            controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);
        }
    }

    @Override
    public Marca recuperarMarcaNaoIntegradoByIdEntidade(Long idEntidade) {
        return marcaRepository.findByIdEntidade(idEntidade);
    }


}
