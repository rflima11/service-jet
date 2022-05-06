package br.com.businesstec.servicejet.service;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.model.entities.Marca;
import br.com.businesstec.model.entities.Produto;

import java.util.List;

public interface MarcaService {

    List<Marca> recuperarMarcasNaoIntegradas();

    List<Marca> recuperarTodasMarcas();

    Marca salvarMarca(Marca marca);

    void integrarMarcas(Marca marca, ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade);


    Marca recuperarMarcaNaoIntegradoByIdEntidade(Long idEntidade);

}
