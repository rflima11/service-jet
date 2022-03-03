package br.com.businesstec.servicejet.datasources.jet.exceptions;

public class OkHttpException extends RuntimeException {

    public OkHttpException(String msg, int statusCode) {
        super(statusCode + msg);
    }

}
