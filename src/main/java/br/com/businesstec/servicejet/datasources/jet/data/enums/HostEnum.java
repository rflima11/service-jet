package br.com.businesstec.servicejet.datasources.jet.data.enums;

public enum HostEnum {

    PRODUTO_HOST("adm-produto-sbx-neo1.plataformaneo.com.br"),
    CLIENTE_HOST(""),
    PEDIDO_HOST("");

    public String value;

    HostEnum(String value) {
        this.value = value;
    }
}
