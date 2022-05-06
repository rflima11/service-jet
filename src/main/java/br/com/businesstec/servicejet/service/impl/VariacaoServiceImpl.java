package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.Variacao;
import br.com.businesstec.model.entities.VariacaoItem;
import br.com.businesstec.model.repository.VariacaoRepository;
import br.com.businesstec.servicejet.client.dto.VariacaoDTO;
import br.com.businesstec.servicejet.client.dto.VariationsDTO;
import br.com.businesstec.servicejet.http.JetVariacao;
import br.com.businesstec.servicejet.service.VariacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class VariacaoServiceImpl implements VariacaoService {

    private static final Logger logger = LoggerFactory.getLogger(VariacaoServiceImpl.class);

    private final VariacaoRepository variacaoRepository;
    private final JetVariacao jetVariacao;

    public VariacaoServiceImpl(VariacaoRepository variacaoRepository, JetVariacao jetVariacao) {
        this.variacaoRepository = variacaoRepository;
        this.jetVariacao = jetVariacao;
    }

    @Override
    public void integrarVariacoes(List<VariacaoDTO> variacoes) {
        variacoes.stream().forEach(v -> {
            try {
                var idMarcaJet = jetVariacao.integrarVariacao(v);
                if (Objects.isNull(idMarcaJet)) {
                    throw new RuntimeException("Não foi possível integrar variacao, id: " + v.getName());
                }
                logger.info("=============================================================================");
                logger.info("MARCA INTEGRADA COM SUCESSO, ID JET:  " + v.getName());
                logger.info("=============================================================================");
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public List<Variacao> recuperarVariacoes(String identificadorOrigem) {
        return variacaoRepository.findByIdentificadorOrigem(identificadorOrigem);
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
}
