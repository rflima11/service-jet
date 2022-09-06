package br.com.businesstec.servicejet.mapper;

import br.com.businesstec.model.entities.Cliente;
import br.com.businesstec.model.entities.ItemPedidoEcommerce;
import br.com.businesstec.model.entities.PedidoEcommerce;
import br.com.businesstec.servicejet.client.dto.PedidoDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Objects;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PedidoEcommerceMapper {

    PedidoEcommerceMapper INSTANCE = Mappers.getMapper(PedidoEcommerceMapper.class);

    @Mapping(source = "idOrder", target = "identificadorOrigem")
    @Mapping(source = "totalShipping", target = "totalFrete")
    PedidoEcommerce map(PedidoDTO entity);

    @AfterMapping
    default void beforeMapper(PedidoDTO pedido, @MappingTarget PedidoEcommerce pedidoEcommerce) {
        if (Objects.isNull(pedido.getCpfCnpj())) {
            pedidoEcommerce.setCpfCnpj("04747103198"); // MOCK CPF
        }
    }
}
