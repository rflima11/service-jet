package br.com.businesstec.servicejet.service;

import br.com.businesstec.model.entities.Cliente;
import br.com.businesstec.model.entities.ControleExecucaoFluxo;
import br.com.businesstec.servicejet.client.dto.ClienteDTO;
import br.com.businesstec.servicejet.client.dto.Queue;

import java.util.List;

public interface ClienteService {

    List<Queue<ClienteDTO>> recuperarQueueClientes();

    Cliente salvar(Cliente cliente, ControleExecucaoFluxo controleExecucaoFluxo, Long idFila);

    void excluirFila(Long idFila);
}
