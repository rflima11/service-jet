package br.com.businesstec.servicejet.factory;

import br.com.businesstec.servicejet.enums.EnumEntidadeStrategy;
import br.com.businesstec.servicejet.service.EntidadeStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class EntidadeStrategyFactory {

    private Map<EnumEntidadeStrategy, EntidadeStrategy> strategies;

    @Autowired
    public EntidadeStrategyFactory(Set<EntidadeStrategy> strategySet) {
        createStrategy(strategySet);
    }

    public EntidadeStrategy findStrategy(EnumEntidadeStrategy strategyName) {
        return strategies.get(strategyName);
    }
    private void createStrategy(Set<EntidadeStrategy> strategySet) {
        strategies = new HashMap<EnumEntidadeStrategy, EntidadeStrategy>();
        strategySet.forEach(
                strategy ->strategies.put(strategy.getEntidadeStrategy(), strategy));
    }
}
