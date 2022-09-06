package br.com.businesstec.servicejet.mapper;

import br.com.businesstec.model.entities.StatusPedidoEcommerce;
import br.com.businesstec.servicejet.client.dto.AtualizaStatusPedidoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StatusPedidoMapper {

    StatusPedidoMapper INSTANCE = Mappers.getMapper(StatusPedidoMapper.class);

    @Mapping(source = "identificadorOrigemProduto", target = "idOrder")
    @Mapping(source = "codigoStatus", target = "statusCode")
    AtualizaStatusPedidoDTO map(StatusPedidoEcommerce entity);
}
