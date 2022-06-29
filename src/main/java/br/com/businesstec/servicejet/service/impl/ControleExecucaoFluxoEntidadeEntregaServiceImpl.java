package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidadeEntrega;
import br.com.businesstec.model.repository.ControleExecucaoFluxoEntidadeEntregaRepository;
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

    @Override
    public ControleExecucaoFluxoEntidadeEntrega registrarErro(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade, String descricaoErro) {
        controleExecucaoFluxoEntidadeService.atualizarIntegracaoErro(controleExecucaoFluxoEntidade);
        return repository.save(new ControleExecucaoFluxoEntidadeEntrega(controleExecucaoFluxoEntidade.getId(), true, descricaoErro));
    }

    @Override
    public ControleExecucaoFluxoEntidadeEntrega encontrarPeloIdControleExecucaoFluxo(Long idControleExecucaoFluxo) {
        return repository.findByIdControleExecucaoFluxoEntidade(idControleExecucaoFluxo).orElseThrow(() -> new RuntimeException("Não encontrado ControleExecucaoFluxoEntidadeEntrega com o ID " + idControleExecucaoFluxo));
    }


}
