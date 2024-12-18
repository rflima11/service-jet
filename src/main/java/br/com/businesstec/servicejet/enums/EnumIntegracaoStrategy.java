package br.com.businesstec.servicejet.enums;

import java.util.Objects;

public enum EnumIntegracaoStrategy {

    VARIACAO_STRATEGY(4L),
    CATEGORIA_STRATEGY(3L),
    MARCA_STRATEGY(2L),
    PRODUTO_STRATEGY(1L),
    PRODUTO_SKU_STRATEGY(5L),
    PRODUTO_IMAGEM(8L),
    PRODUTO_ESTOQUE(7L),
    PRODUTO_PRECO(6L),
    CLIENTES_STRATEGY(9L);

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

    static class teste {
        public static void main(String[] args) {
            var strategy =EnumIntegracaoStrategy.getStrategyByIdEntidade(9L);
            System.out.println(strategy);
        }
    }
}
