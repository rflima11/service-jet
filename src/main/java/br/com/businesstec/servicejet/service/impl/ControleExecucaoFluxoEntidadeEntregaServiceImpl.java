package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.servicejet.model.ControleExecucaoFluxoEntidade;
import br.com.businesstec.servicejet.model.ControleExecucaoFluxoEntidadeEntrega;
import br.com.businesstec.servicejet.repository.ControleExecucaoFluxoEntidadeEntregaRepository;
import br.com.businesstec.servicejet.service.ControleExecucaoFluxoEntidadeEntregaService;
import br.com.businesstec.servicejet.service.ControleExecucaoFluxoEntidadeService;
import org.springframework.stereotype.Service;

@Service
public class ControleExecucaoFluxoEntidadeEntregaServiceImpl implements ControleExecucaoFluxoEntidadeEntregaService {

    private final ControleExecucaoFluxoEntidadeEntregaRepository repository;
    private final ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService;

    public ControleExecucaoFluxoEntidadeEntregaServiceImpl(ControleExecucaoFluxoEntidadeEntregaRepository repository, ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService) {
        this.repository = repository;
        this.controleExecucaoFluxoEntidadeService = controleExecucaoFluxoEntidadeService;
    }

    @Override
    public ControleExecucaoFluxoEntidadeEntrega registrarExecucao(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);
        return repository.save(new ControleExecucaoFluxoEntidadeEntrega(controleExecucaoFluxoEntidade.getId(), false));
    }
}
