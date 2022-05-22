package br.com.businesstec.servicejet.listenner.event;

import br.com.businesstec.model.entities.ControleExecucaoFluxo;
import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import org.springframework.context.ApplicationEvent;

public class ControleExecucaoFluxoEntidadeEvent extends ApplicationEvent {

    private ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade;

    public ControleExecucaoFluxoEntidadeEvent(Object source, ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        super(controleExecucaoFluxoEntidade);
        this.controleExecucaoFluxoEntidade = controleExecucaoFluxoEntidade;
    }

    public ControleExecucaoFluxoEntidade getControleExecucaoFluxoEntidade() {
        return controleExecucaoFluxoEntidade;
    }
}