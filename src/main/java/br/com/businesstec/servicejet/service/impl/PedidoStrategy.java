package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ControleExecucaoFluxo;
import br.com.businesstec.servicejet.enums.EnumEntidadeStrategy;
import br.com.businesstec.servicejet.mapper.ItemPedidoMapper;
import br.com.businesstec.servicejet.mapper.PedidoEcommerceMapper;
import br.com.businesstec.servicejet.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PedidoStrategy implements EntidadeStrategy {

    private static final Logger logger = LoggerFactory.getLogger(PedidoStrategy.class);


    private final PedidoService pedidoService;
    private final ItemPedidoService itemPedidoService;
    private final ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService;
    private final PedidoEcommerceService pedidoEcommerceService;
    private final ItemPedidoMapper itemPedidoMapper;
    private final PedidoEcommerceMapper pedidoEcommerceMapper;

    public PedidoStrategy(PedidoService pedidoService, ItemPedidoService itemPedidoService, ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService, PedidoEcommerceService pedidoEcommerceService) {
        this.pedidoService = pedidoService;
        this.itemPedidoService = itemPedidoService;
        this.controleExecucaoFluxoEntidadeService = controleExecucaoFluxoEntidadeService;
        this.pedidoEcommerceService = pedidoEcommerceService;
        pedidoEcommerceMapper = PedidoEcommerceMapper.INSTANCE;
        itemPedidoMapper = ItemPedidoMapper.INSTANCE;
    }

    @Override
    public void executar(ControleExecucaoFluxo controleExecucaoFluxo) {
        try {
            var controleFluxoEntidade = controleExecucaoFluxoEntidadeService.encontrarPeloIdControleFluxo(controleExecucaoFluxo.getId());
            Thread.sleep(300);
            var pedidos = pedidoService.recuperarQueuePedidos();
            pedidos.stream().forEach(queue -> {
                try {
                var pedido = queue.getEntity();
                var pedidoEcommerce = pedidoEcommerceMapper.map(pedido);
                var pedidoEcommerceSalvo = pedidoEcommerceService.salvar(pedidoEcommerce);
                var itensPedido = pedido.getOrderItems().stream().map(o -> itemPedidoMapper.map(o)).collect(Collectors.toList());
                itensPedido.stream().forEach(itemPedido -> {
                    itemPedidoService.salvar(itemPedido, pedidoEcommerceSalvo.getId());
                });
                var idQueue = queue.getIdQueue();
                controleExecucaoFluxoEntidadeService.registrar(controleExecucaoFluxo.getId(), pedidoEcommerceSalvo.getIdEntidade(), idQueue);

                Thread.sleep(300);
                pedidoService.deletarPedidoFila(idQueue);
                logger.info(String.format("PEDIDO ID: %s SALVOS COM SUCESSO!", pedido.getIdOrder()));
                } catch (InterruptedException e) {
                    logger.error(e.getMessage());
                    throw new RuntimeException(e);
                }
            });
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        };
    }

    @Override
    public EnumEntidadeStrategy getEntidadeStrategy() {
        return EnumEntidadeStrategy.PEDIDO_STRATEGY;
    }
}
