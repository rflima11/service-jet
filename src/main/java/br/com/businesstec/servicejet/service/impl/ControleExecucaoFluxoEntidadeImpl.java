package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.servicejet.model.ControleExecucaoFluxoEntidade;
import br.com.businesstec.servicejet.repository.ControleExecucaoFluxoEntidadeRepository;
import br.com.businesstec.servicejet.service.ControleExecucaoFluxoEntidadeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControleExecucaoFluxoEntidadeImpl implements ControleExecucaoFluxoEntidadeService {

    private final ControleExecucaoFluxoEntidadeRepository controleExecucaoFluxoEntidadeRepository;

    public ControleExecucaoFluxoEntidadeImpl(ControleExecucaoFluxoEntidadeRepository controleExecucaoFluxoEntidadeRepository) {
        this.controleExecucaoFluxoEntidadeRepository = controleExecucaoFluxoEntidadeRepository;
    }

    @Override
    public List<ControleExecucaoFluxoEntidade> recuperarControlesFluxos() {
        return controleExecucaoFluxoEntidadeRepository.findByIntegradoFalse();
    }

    @Override
    public ControleExecucaoFluxoEntidade atualizarIntegracao(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        controleExecucaoFluxoEntidade.setIntegrado(true);
        return controleExecucaoFluxoEntidadeRepository.save(controleExecucaoFluxoEntidade);
    }

    @Override
    public Long recuperarTipoEntidade(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        return controleExecucaoFluxoEntidadeRepository.findTipoEntidadePorCtrlFluxo(controleExecucaoFluxoEntidade.getId());
    }
}
