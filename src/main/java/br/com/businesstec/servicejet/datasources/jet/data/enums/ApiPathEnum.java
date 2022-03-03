package br.com.businesstec.servicejet.datasources.jet.data.enums;

public enum ApiPathEnum {

    GET_QUEUE_PRODUTOS("/adm_products/GetQueue");
//    /adm_products/GetQueue

    String value;

    ApiPathEnum(String value) {
        this.value= value;
    }

    public String getValue() {
        return value;
    }
}
