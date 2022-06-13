package br.com.businesstec.servicejet.exceptions;

public class ProdutoNaoCadastradoException extends RuntimeException {

    public ProdutoNaoCadastradoException(String msg) {
        super(msg);
    }
}
