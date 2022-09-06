package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ItemPedidoEcommerce;
import br.com.businesstec.model.repository.ItemPedidoEcommerceRepository;
import br.com.businesstec.servicejet.service.ItemPedidoService;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoServiceImpl implements ItemPedidoService {

    private final ItemPedidoEcommerceRepository repository;

    public ItemPedidoServiceImpl(ItemPedidoEcommerceRepository repository) {
        this.repository = repository;
    }

    @Override
    public ItemPedidoEcommerce salvar(ItemPedidoEcommerce itemPedido, Long idPedido) {
        itemPedido.setIdPedidoEcommerce(idPedido);
        return repository.save(itemPedido);
    }
}
