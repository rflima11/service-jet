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
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    @Retryable(FeignException.class)
    public void integrarVariacao(VariacaoDTO variacao, ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        var accessToken = tokenService.getAccessToken(jetProperties.getProduto());
        variacaoJet.adicionarNovaVariacao(accessToken, variacao);

        controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);

    }

    @Recover
    private void recover(FeignException e) throws JsonProcessingException {
        try {
            if (e.contentUTF8().contains(MENSAGEM_ERRO_VARIACAO_JA_CADASTRADA) || e.contentUTF8().contains(MENSAGEM_ERRO_CODIGO_JA_CADASTRADO)) {
                var body = new String(e.request().body(), StandardCharsets.US_ASCII);
                var accessToken = tokenService.getAccessToken(jetProperties.getProduto());
                var variacaoDto = objectMapper.readValue(body, VariacaoDTO.class);
                variacaoDto = verificarSeVariacaoJaEstaCadastrado(variacaoJet.getVariacoes(accessToken).getBody(), variacaoDto);
                var variacao = variacaoRepository.findByIdentificadorOrigem(variacaoDto.getExternalId());
                var controleExecucaoFluxoEntidade = controleExecucaoFluxoEntidadeService
                        .encontrarFluxoExecucaoEntidadeByIdEntidade(variacao.orElseThrow(() -> new RuntimeException("Não encontrado controle fluxo execução")).getIdEntidade());
                Thread.sleep(300);
                variacaoJet.atualizarVariacao(accessToken, variacaoDto);
                controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private VariacaoDTO verificarSeVariacaoJaEstaCadastrado(List<Queue<VariacaoDTO>> filaMarca, VariacaoDTO variacaoDTO) {
        var objetoVariacao = filaMarca.stream().filter(f -> {
            var variacaoDtoFila = f.getEntity();
            return Objects.nonNull(variacaoDtoFila.getExternalId()) &&  variacaoDtoFila.getExternalId().equals(variacaoDTO.getExternalId());

        }).collect(Collectors.toList());
        var obj = objetoVariacao.stream().findFirst().get().getEntity();

        for (VariationsDTO variacao: obj.getVariations()) {
            var variacoes = variacaoDTO.getVariations();
            for(int i = 0; i < variacoes.size(); i++) {
                if (variacoes.get(i).getName().equals(variacao.getName()) && variacoes.get(i).getExternalId().equals(variacao.getExternalId())) {
                    variacoes.remove(variacoes.get(i));
                }
            }
        }

//        obj.getVariations().stream().forEach(v -> {
//            var variacoes = variacaoDTO.getVariations();
//            variacoes.forEach(vr -> {
//                if (v.getName().equals(vr.getName())) {
//                    variacoes.remove(vr);
//                }
//            });
//        });

        variacaoDTO.setIdReference(obj.getIdReference());
        return variacaoDTO;
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
}
