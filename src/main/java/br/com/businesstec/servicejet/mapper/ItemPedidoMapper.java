package br.com.businesstec.servicejet.mapper;

import br.com.businesstec.model.entities.EstoqueProduto;
import br.com.businesstec.model.entities.ItemPedidoEcommerce;
import br.com.businesstec.model.entities.PedidoEcommerce;
import br.com.businesstec.servicejet.client.dto.AtualizarEstoqueDTO;
import br.com.businesstec.servicejet.client.dto.OrderItemDTO;
import br.com.businesstec.servicejet.client.dto.PedidoDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Objects;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemPedidoMapper {

    ItemPedidoMapper INSTANCE = Mappers.getMapper(ItemPedidoMapper.class);

    @Mapping(source = "idOrderItem", target = "idPedidoEcommerce")
    @Mapping(source = "externalIdProduct", target = "identificadorOrigemProduto")
    @Mapping(source = "quantity", target = "quantidade")
    @Mapping(source = "unitPrice", target = "precoUnitario")
    ItemPedidoEcommerce map(OrderItemDTO entity);

    @AfterMapping
    default void afterMap(OrderItemDTO dto, @MappingTarget ItemPedidoEcommerce entity) {
        if (Objects.nonNull(dto.getExternalIdSku())) {
            entity.setIdentificadorOrigemProduto(dto.getExternalIdSku());
        }
    }

}
