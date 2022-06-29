package br.com.businesstec.servicejet.mapper;

import br.com.businesstec.model.entities.PrecoProduto;
import br.com.businesstec.servicejet.client.dto.ProductPriceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProdutoPriceMapper {

    ProdutoPriceMapper INSTANCE = Mappers.getMapper(ProdutoPriceMapper.class);

    ProductPriceDTO map(PrecoProduto entity);


}
