package br.com.businesstec.servicejet.enums;

import java.util.Objects;

public enum EnumEntidadeStrategy {

    PEDIDO_STRATEGY(5L),
    CLIENTES_STRATEGY(9L);

    private Long value;

    EnumEntidadeStrategy(long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    EnumEntidadeStrategy getStrategyById(int id) {
        return null;
    }

    public static EnumEntidadeStrategy getStrategyByIdEntidade(Long idEntidade) {
        for (EnumEntidadeStrategy e : values()) {
            if (Objects.equals(e.value, idEntidade)) {
                return e;
            }
        }
        return null;
    }
}
