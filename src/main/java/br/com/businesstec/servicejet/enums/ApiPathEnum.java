package br.com.businesstec.servicejet.enums;

public enum ApiPathEnum {

    GET_QUEUE_PRODUTOS("/adm_products/GetQueue"),
    INSERIR_MARCA("api/v1/adm_brands"),
    INSERIR_VARIACAO("api/v1/adm_variations");

    public String value;

    ApiPathEnum(String value) {
        this.value= value;
    }

    public String getValue() {
        return value;
    }
}
