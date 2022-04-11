package br.com.businesstec.servicejet.scheduler;

import br.com.businesstec.servicejet.enums.EnumIntegracaoStrategy;
import br.com.businesstec.servicejet.factory.StrategyFactory;
import br.com.businesstec.servicejet.http.JetMarca;
import br.com.businesstec.servicejet.mapper.MarcaMapper;
import br.com.businesstec.servicejet.service.ControleExecucaoFluxoEntidadeService;
import br.com.businesstec.servicejet.service.MarcaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class BuscarEntidadesScheduler {

    private static final Logger logger = LoggerFactory.getLogger(BuscarEntidadesScheduler.class);

    private final ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService;
    private final MarcaService marcaService;
    private final StrategyFactory strategyFactory;

    public BuscarEntidadesScheduler(ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService, MarcaService marcaService, JetMarca jetMarca, StrategyFactory strategyFactory) {
        this.controleExecucaoFluxoEntidadeService = controleExecucaoFluxoEntidadeService;
        this.marcaService = marcaService;
        this.strategyFactory = strategyFactory;
    }

    @Scheduled(fixedRate = 10000)
    public void integrarEntidades() {
        var controles = controleExecucaoFluxoEntidadeService.recuperarControlesFluxos();

        controles.stream().forEach(c -> {
            var strategy = strategyFactory
                    .findStrategy(EnumIntegracaoStrategy.getStrategyByIdEntidade(
                            controleExecucaoFluxoEntidadeService.recuperarTipoEntidade(c)));
            strategy.executar(c);
        });
    }
}
