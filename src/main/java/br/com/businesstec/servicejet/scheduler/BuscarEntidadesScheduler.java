package br.com.businesstec.servicejet.scheduler;

import br.com.businesstec.servicejet.enums.EnumIntegracaoStrategy;
import br.com.businesstec.servicejet.factory.IntegracaoStrategyFactory;
import br.com.businesstec.servicejet.http.JetMarca;
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
    private final IntegracaoStrategyFactory strategyFactory;

    public BuscarEntidadesScheduler(ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService, MarcaService marcaService, JetMarca jetMarca, IntegracaoStrategyFactory strategyFactory) {
        this.controleExecucaoFluxoEntidadeService = controleExecucaoFluxoEntidadeService;
        this.marcaService = marcaService;
        this.strategyFactory = strategyFactory;
    }

    @Scheduled(fixedRate = 10000)
    public void integrarEntidades() {
        var controles = controleExecucaoFluxoEntidadeService.recuperarControlesFluxos();

        if (controles.isEmpty()) {
            logger.info("NÃO FORAM ENCONTRADOS NOVOS REGISTROS A INTEGRAR");
        } else {
            logger.info("FORAM ENCONTRADOS " + controles.size() + " REGISTROS NOVOS A SEREM INTEGRADOS");
        }
        controles.stream().forEach(c -> {

            var strategy = strategyFactory
                    .findStrategy(EnumIntegracaoStrategy.getStrategyByIdEntidade(
                            controleExecucaoFluxoEntidadeService.recuperarTipoEntidade(c)));
            strategy.executar(c);
        });
    }
}
