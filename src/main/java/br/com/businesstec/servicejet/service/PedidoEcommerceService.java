package br.com.businesstec.servicejet.service;

import br.com.businesstec.model.entities.PedidoEcommerce;
import br.com.businesstec.model.entities.StatusPedidoEcommerce;
import br.com.businesstec.servicejet.client.dto.AtualizaStatusPedidoDTO;

public interface PedidoEcommerceService {

    PedidoEcommerce salvar(PedidoEcommerce pedido);

    void alterarStatusPedido(StatusPedidoEcommerce statusPedidoEcommerce);

    StatusPedidoEcommerce encontrarPeloIdEntidade(Long idEntidade);
}
