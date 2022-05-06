package br.com.businesstec.servicejet.service;

import br.com.businesstec.servicejet.client.dto.PedidoDTO;
import br.com.businesstec.servicejet.client.dto.Queue;

import java.util.List;

public interface PedidoService {

    List<Queue<PedidoDTO>> recuperarQueuePedidos();
}
