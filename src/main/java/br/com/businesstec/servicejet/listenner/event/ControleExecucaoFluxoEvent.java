package br.com.businesstec.servicejet.listenner.event;

import br.com.businesstec.model.entities.ControleExecucaoFluxo;
import br.com.businesstec.servicejet.listenner.dto.ControleExecucaoFluxoDTO;
import org.springframework.context.ApplicationEvent;

public class ControleExecucaoFluxoEvent extends ApplicationEvent {

    private ControleExecucaoFluxoDTO controleExecucaoFluxo;

    public ControleExecucaoFluxoEvent(Object source, ControleExecucaoFluxoDTO controleExecucaoFluxo) {
        super(controleExecucaoFluxo);
        this.controleExecucaoFluxo = controleExecucaoFluxo;
    }

    public ControleExecucaoFluxoDTO getControleExecucaoFluxo() {
        return controleExecucaoFluxo;
    }
}