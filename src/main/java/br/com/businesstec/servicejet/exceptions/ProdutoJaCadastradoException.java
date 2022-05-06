package br.com.businesstec.servicejet.exceptions;

import br.com.businesstec.servicejet.client.dto.ProdutoDTO;

public class ProdutoJaCadastradoException extends RuntimeException {

    private ProdutoDTO produtoDTO;

    public ProdutoJaCadastradoException(String msg) {
        super(msg);
    }

    public ProdutoDTO getProdutoDTO() {
        return produtoDTO;
    }
}
