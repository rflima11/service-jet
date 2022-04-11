package br.com.businesstec.servicejet.enums;

import java.util.Objects;

public enum EnumIntegracaoStrategy {

    VARIACAO_STRATEGY(4L),
    CATEGORIA_STRATEGY(3L),
    MARCA_STRATEGY(2L);

    private Long value;

    EnumIntegracaoStrategy(long value) {
        this.value = value;
    }

    EnumIntegracaoStrategy getStrategyById(int id) {
        return null;
    }

    public static EnumIntegracaoStrategy getStrategyByIdEntidade(Long idEntidade) {
        for (EnumIntegracaoStrategy e : values()) {
            if (Objects.equals(e.value, idEntidade)) {
                return e;
            }
        }
        return null;
    }
}
