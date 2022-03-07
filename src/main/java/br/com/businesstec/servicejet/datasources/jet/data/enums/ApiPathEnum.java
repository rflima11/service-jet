package br.com.businesstec.servicejet.datasources.jet.data.enums;

public enum ApiPathEnum {

    GET_QUEUE_PRODUTOS("/adm_products/GetQueue"),
    INSERIR_MARCA("api/v1/adm_brands");

    public String value;

    ApiPathEnum(String value) {
        this.value= value;
    }

    public String getValue() {
        return value;
    }
}
