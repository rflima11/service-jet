package br.com.businesstec.servicejet.mapper;

import br.com.businesstec.model.entities.Cliente;
import br.com.businesstec.model.entities.EstoqueProduto;
import br.com.businesstec.servicejet.client.dto.AtualizarEstoqueDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EstoqueMapper {


    EstoqueMapper INSTANCE = Mappers.getMapper(EstoqueMapper.class);

    @Mapping(source = "identificadorOrigemProduto", target = "externalId")
    @Mapping(source = "estoque", target = "stock")
    @Mapping(source = "identificadorOrigemSku", target = "skuStock.externalId")
    AtualizarEstoqueDTO map(EstoqueProduto entity);

    @AfterMapping
    default void afterMap(EstoqueProduto entity, @MappingTarget AtualizarEstoqueDTO dto) {
        var externalIdSku = dto.getSkuStock().getExternalId();
        if (externalIdSku.equals("0")) {
            dto.setSkuStock(null);
        }
        var externalIdProduto = dto.getExternalId();
        if (externalIdProduto.equals("0")) {
            dto.setExternalId(null);
            dto.setStock(null);

            dto.setIdProduct(null);
            dto.getSkuStock().setStock(entity.getEstoque());
            dto.getSkuStock().setIdSku(null);
        }

    }

}
