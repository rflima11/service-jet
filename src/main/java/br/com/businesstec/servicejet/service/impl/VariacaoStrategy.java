package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.model.repository.VariacaoItemRepository;
import br.com.businesstec.servicejet.client.dto.ProdutoDTO;
import br.com.businesstec.servicejet.enums.EnumIntegracaoStrategy;
import br.com.businesstec.servicejet.mapper.VariacaoItemMapper;
import br.com.businesstec.servicejet.mapper.VariacaoMapper;
import br.com.businesstec.servicejet.service.ControleExecucaoFluxoEntidadeEntregaService;
import br.com.businesstec.servicejet.service.IntegracaoStrategy;
import br.com.businesstec.servicejet.service.VariacaoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VariacaoStrategy implements IntegracaoStrategy {

    private static final Logger logger = LoggerFactory.getLogger(VariacaoStrategy.class);

    private final ControleExecucaoFluxoEntidadeEntregaService controleExecucaoFluxoEntidadeEntregaService;
    private final VariacaoService variacaoService;
    private final VariacaoItemRepository variacaoItemRepository;
    private final VariacaoMapper variacaoMapper;
    private final VariacaoItemMapper variacaoItemMapper;
    
    public VariacaoStrategy(ControleExecucaoFluxoEntidadeEntregaService controleExecucaoFluxoEntidadeEntregaService, VariacaoService variacaoService, VariacaoItemRepository variacaoItemRepository) {
        this.controleExecucaoFluxoEntidadeEntregaService = controleExecucaoFluxoEntidadeEntregaService;
        this.variacaoService = variacaoService;
        this.variacaoItemRepository = variacaoItemRepository;
        variacaoMapper = VariacaoMapper.INSTANCE;
        variacaoItemMapper = VariacaoItemMapper.INSTANCE;
    }

    @Override
    public void executar(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        var variacaoModel = variacaoService.recuperarVariacaoNaoIntegradasByIdEntidade(controleExecucaoFluxoEntidade.getIdEntidade());
        var variacaoDto = variacaoMapper.map(variacaoModel);


        var variacoesItem = variacaoItemRepository.findByIdVariacao(variacaoModel.getId());

        var variacoesItemDto = variacoesItem.stream().map(variacaoItemMapper::map).collect(Collectors.toList());




        variacaoDto.setVariations(variacoesItemDto);
        logger.info("=============================================================================");
//        logger.info("VARIACOES ENCONTRADAS: " + variacaos.size());
        logger.info("=============================================================================");

        variacaoService.integrarVariacao(variacaoDto, controleExecucaoFluxoEntidade);

    }



    @Override
    public EnumIntegracaoStrategy getIntegracaoStrategy() {
        return EnumIntegracaoStrategy.VARIACAO_STRATEGY;
    }
}
