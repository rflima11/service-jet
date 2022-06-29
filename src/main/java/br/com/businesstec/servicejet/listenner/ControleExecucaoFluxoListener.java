package br.com.businesstec.servicejet.listenner;

import br.com.businesstec.servicejet.enums.EnumIntegracaoStrategy;
import br.com.businesstec.servicejet.factory.IntegracaoStrategyFactory;
import br.com.businesstec.servicejet.listenner.event.ControleExecucaoFluxoEntidadeEvent;
import br.com.businesstec.servicejet.service.EntidadeService;
import br.com.businesstec.servicejet.service.impl.MarcaStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ControleExecucaoFluxoListener implements ApplicationListener<ControleExecucaoFluxoEntidadeEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ControleExecucaoFluxoListener.class);


    private final EntidadeService entidadeService;
    private final IntegracaoStrategyFactory integracaoStrategyFactory;

    public ControleExecucaoFluxoListener(EntidadeService entidadeService, IntegracaoStrategyFactory integracaoStrategyFactory) {
        this.entidadeService = entidadeService;
        this.integracaoStrategyFactory = integracaoStrategyFactory;
    }

    @Override
    public void onApplicationEvent(ControleExecucaoFluxoEntidadeEvent event) {
        var controleExecucaoFluxoEntidade = event.getControleExecucaoFluxoEntidade();
        var entidade = entidadeService.encontrarIdEntidade(controleExecucaoFluxoEntidade.getIdEntidade());
        var enumIntegracaoStrategy = EnumIntegracaoStrategy.getStrategyByIdEntidade(entidade.getIdEntidade());
        var strategy = integracaoStrategyFactory.findStrategy(enumIntegracaoStrategy);

        logger.info("==========================================================");
        logger.info(String.format("Nova mensagem detectada ID: %s ", controleExecucaoFluxoEntidade.getIdControleExecucaoFluxo()));
        logger.info(String.format("ID Entidade: %s ", controleExecucaoFluxoEntidade.getIdEntidade()));
        logger.info(String.format("STRATEGY: %s ", enumIntegracaoStrategy));
        logger.info("==========================================================");


        strategy.executar(controleExecucaoFluxoEntidade);
    }
}
