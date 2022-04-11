package br.com.businesstec.servicejet.service;

import br.com.businesstec.servicejet.model.ControleExecucaoFluxoEntidade;
import br.com.businesstec.servicejet.model.Marca;

import java.util.List;

public interface MarcaService {

    List<Marca> recuperarMarcasNaoIntegradas();

    List<Marca> recuperarTodasMarcas();

    Marca salvarMarca(Marca marca);

    void integrarMarcas(List<Marca> marcas, ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade);

}
