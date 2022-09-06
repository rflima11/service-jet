package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.servicejet.enums.EnumIntegracaoStrategy;
import br.com.businesstec.servicejet.service.ControleExecucaoFluxoEntidadeEntregaService;
import br.com.businesstec.servicejet.service.ControleExecucaoFluxoEntidadeService;
import br.com.businesstec.servicejet.service.IntegracaoStrategy;
import br.com.businesstec.servicejet.service.PedidoEcommerceService;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class StatusPedidoStrategy implements IntegracaoStrategy  {

    private final PedidoEcommerceService pedidoService;
    private final ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService;
    private final ControleExecucaoFluxoEntidadeEntregaService execucaoFluxoEntidadeEntregaService;

    public StatusPedidoStrategy(PedidoEcommerceService pedidoService, ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService, ControleExecucaoFluxoEntidadeEntregaService execucaoFluxoEntidadeEntregaService) {
        this.pedidoService = pedidoService;
        this.controleExecucaoFluxoEntidadeService = controleExecucaoFluxoEntidadeService;
        this.execucaoFluxoEntidadeEntregaService = execucaoFluxoEntidadeEntregaService;
    }

    @Retryable(
            value = RuntimeException.class,
            maxAttemptsExpression = "${config.retry.maxAttempts}",
            backoff = @Backoff(delayExpression = "${config.retry.maxDelay}")
    )
    @Override
    public void executar(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        var statusPedidoSalvo = pedidoService.encontrarPeloIdEntidade(controleExecucaoFluxoEntidade.getIdEntidade());

        pedidoService.alterarStatusPedido(statusPedidoSalvo);
        controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);

        System.out.println("feito");
    }

    @Override
    public EnumIntegracaoStrategy getIntegracaoStrategy() {
        return EnumIntegracaoStrategy.STATUS_PEDIDO;
    }

    @Recover
    private void recover(RuntimeException e, ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);
        execucaoFluxoEntidadeEntregaService.registrarErro(controleExecucaoFluxoEntidade, e.getMessage());
    }
}
