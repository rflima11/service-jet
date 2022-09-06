package br.com.businesstec.servicejet.service;

import br.com.businesstec.model.entities.ItemPedidoEcommerce;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoService {

    ItemPedidoEcommerce salvar(ItemPedidoEcommerce itemPedido, Long idPedido);
}
