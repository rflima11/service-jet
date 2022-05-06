package br.com.businesstec.servicejet.factory;

import br.com.businesstec.servicejet.enums.EnumIntegracaoStrategy;
import br.com.businesstec.servicejet.service.IntegracaoStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class IntegracaoStrategyFactory {

    private Map<EnumIntegracaoStrategy, IntegracaoStrategy> strategies;

    @Autowired
    public IntegracaoStrategyFactory(Set<IntegracaoStrategy> strategySet) {
        createStrategy(strategySet);
    }

    public IntegracaoStrategy findStrategy(EnumIntegracaoStrategy strategyName) {
        return strategies.get(strategyName);
    }
    private void createStrategy(Set<IntegracaoStrategy> strategySet) {
        strategies = new HashMap<EnumIntegracaoStrategy, IntegracaoStrategy>();
        strategySet.forEach(
                strategy ->strategies.put(strategy.getIntegracaoStrategy(), strategy));
    }
}
