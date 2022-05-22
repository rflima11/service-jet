package br.com.businesstec.servicejet.mapper;

import br.com.businesstec.model.entities.ProdutoSku;
import br.com.businesstec.servicejet.client.dto.ProdutoSkuDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProdutoSkuMapper {

    ProdutoSkuMapper INSTANCE = Mappers.getMapper(ProdutoSkuMapper.class);

    @Mapping(source = "identificadorOrigem", target = "externalId")
    @Mapping(source = "codigo", target = "skuCode")
    @Mapping(source = "preco", target = "price")
    @Mapping(source = "padrao", target = "standard")
    @Mapping(source = "estoque", target = "stock")
    @Mapping(source = "peso", target = "cubing.peso")
    @Mapping(source = "altura", target = "cubing.altura")
    @Mapping(source = "largura", target = "cubing.largura")
    @Mapping(source = "comprimento", target = "cubing.comprimento")
    @Mapping(source = "diametro", target = "cubing.diametro")
    ProdutoSkuDTO map(ProdutoSku entity);

}
