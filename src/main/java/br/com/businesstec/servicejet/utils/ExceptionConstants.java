package br.com.businesstec.servicejet.utils;

/**
 * Constantes utilizadas para mensagens de Exceptions
 */
public enum ExceptionConstants {

    ERRO_CONVERTER_DTO("Não foi possível converter o DTO "),
    ENTIDADE_NAO_INTEGRADA("Não foi possível integrar a entidade"),
    OK_HTTP_EXCEPTION_MSG("Não foi possível realizar a chamada");


    String value;

    ExceptionConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}