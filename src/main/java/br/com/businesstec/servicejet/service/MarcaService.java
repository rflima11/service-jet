package br.com.businesstec.servicejet.service;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.model.entities.Marca;
import br.com.businesstec.model.entities.Produto;
import br.com.businesstec.servicejet.client.dto.MarcaDTO;
import br.com.businesstec.servicejet.client.dto.Queue;

import java.util.List;
import java.util.Optional;

public interface MarcaService {

    List<Marca> recuperarMarcasNaoIntegradas();

    List<Marca> recuperarTodasMarcas();

    Marca salvarMarca(Marca marca);

    void integrarMarcas(Marca marca, ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade);


    Marca recuperarMarcaNaoIntegradoByIdEntidade(Long idEntidade);

}
