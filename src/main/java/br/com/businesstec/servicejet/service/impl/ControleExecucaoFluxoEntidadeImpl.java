package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.model.repository.ControleExecucaoFluxoEntidadeRepository;
import br.com.businesstec.servicejet.service.ControleExecucaoFluxoEntidadeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ControleExecucaoFluxoEntidadeImpl implements ControleExecucaoFluxoEntidadeService {

    private final ControleExecucaoFluxoEntidadeRepository controleExecucaoFluxoEntidadeRepository;

    public ControleExecucaoFluxoEntidadeImpl(ControleExecucaoFluxoEntidadeRepository controleExecucaoFluxoEntidadeRepository) {
        this.controleExecucaoFluxoEntidadeRepository = controleExecucaoFluxoEntidadeRepository;
    }

    @Override
    public List<ControleExecucaoFluxoEntidade> recuperarControlesFluxos() {
        return controleExecucaoFluxoEntidadeRepository.findByIntegradoFalseOrderByIdAsc();
    }

    @Override
    public ControleExecucaoFluxoEntidade atualizarIntegracao(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        controleExecucaoFluxoEntidade.setIntegrado(true);
        return controleExecucaoFluxoEntidadeRepository.save(controleExecucaoFluxoEntidade);
    }

    @Override
    public ControleExecucaoFluxoEntidade atualizarIntegracaoErro(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        controleExecucaoFluxoEntidade.setIntegrado(false);
        return controleExecucaoFluxoEntidadeRepository.save(controleExecucaoFluxoEntidade);    }


    @Override
    public ControleExecucaoFluxoEntidade atualizarIntegracao(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade, Long idFila) {
        controleExecucaoFluxoEntidade.setIdFila(idFila);
        controleExecucaoFluxoEntidade.setIntegrado(true);
        return controleExecucaoFluxoEntidadeRepository.save(controleExecucaoFluxoEntidade);
    }

    @Override
    public Long recuperarTipoEntidade(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        return controleExecucaoFluxoEntidadeRepository.findTipoEntidadePorCtrlFluxo(controleExecucaoFluxoEntidade.getId());
    }

    @Override
    public Optional<ControleExecucaoFluxoEntidade> encontrarPeloIdControleFluxo(Long idControleFluxo) {
        return controleExecucaoFluxoEntidadeRepository.findByIdControleExecucaoFluxo(idControleFluxo);
    }

    @Override
    public ControleExecucaoFluxoEntidade registrar(Long idControleExecucaoFluxo, Long idEntidade) {
        return controleExecucaoFluxoEntidadeRepository.save(new ControleExecucaoFluxoEntidade(idControleExecucaoFluxo, idEntidade));
    }

    @Override
    public ControleExecucaoFluxoEntidade registrar(Long idControleExecucaoFluxo, Long idEntidade, Long idFila) {
        var optControleExecucaoFluxo = controleExecucaoFluxoEntidadeRepository.findByIdEntidade(idEntidade);
        var controleExecucaoFluxoEntidade = new ControleExecucaoFluxoEntidade(idControleExecucaoFluxo, idEntidade);
        if (optControleExecucaoFluxo.isPresent())  {
            controleExecucaoFluxoEntidade.setId(optControleExecucaoFluxo.get().getId());
        }
        return controleExecucaoFluxoEntidadeRepository.save(controleExecucaoFluxoEntidade);
    }

    @Override
    public ControleExecucaoFluxoEntidade encontrarFluxoExecucaoEntidadeByIdEntidade(Long idEntidade) {
        return controleExecucaoFluxoEntidadeRepository.findByIdEntidadeAndIntegradoFalse(idEntidade);
    }
}
