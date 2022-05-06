package br.com.businesstec.servicejet.listenner;

import br.com.businesstec.servicejet.enums.EnumEntidadeStrategy;
import br.com.businesstec.servicejet.factory.EntidadeStrategyFactory;
import br.com.businesstec.servicejet.listenner.event.ControleExecucaoFluxoEvent;
import br.com.businesstec.servicejet.service.ControleExecucaoFluxoEntidadeService;
import br.com.businesstec.servicejet.service.ControleFluxoService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ControleExecucaoFluxoListenner implements ApplicationListener<ControleExecucaoFluxoEvent> {

    private final ControleFluxoService controleFluxoService;
    private final ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService;
    private final EntidadeStrategyFactory strategyFactory;

    public ControleExecucaoFluxoListenner(ControleFluxoService controleFluxoService,
                                          ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService, EntidadeStrategyFactory strategyFactory) {
        this.controleFluxoService = controleFluxoService;
        this.controleExecucaoFluxoEntidadeService = controleExecucaoFluxoEntidadeService;
        this.strategyFactory = strategyFactory;
    }

    @Override
    public void onApplicationEvent(ControleExecucaoFluxoEvent event) {
        var controleExecucaoFluxo = event.getControleExecucaoFluxo();
        var idEntidade = controleFluxoService.recuperarFluxoPeloId(controleExecucaoFluxo.getIdControleFluxo()).getIdTipoEntidade();
        var enumIntegracaoStrategy = EnumEntidadeStrategy
                        .getStrategyByIdEntidade(idEntidade);
        var strategy = strategyFactory.findStrategy(enumIntegracaoStrategy);
        strategy.executar();
    }
}
