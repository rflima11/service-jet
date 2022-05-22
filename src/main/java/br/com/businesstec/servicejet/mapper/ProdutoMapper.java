package br.com.businesstec.servicejet.mapper;

import br.com.businesstec.model.entities.Marca;
import br.com.businesstec.model.entities.Produto;
import br.com.businesstec.servicejet.client.dto.MarcaDTO;
import br.com.businesstec.servicejet.client.dto.ProdutoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProdutoMapper {

    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    @Mapping(source = "codigo", target = "code")
    @Mapping(source = "descricaoResumida", target = "name")
    @Mapping(source = "estoque", target = "stock")
    @Mapping(source = "preco", target = "price")
    @Mapping(source = "identificadorOrigem", target = "externalId")
    @Mapping(source = "precoPromocional", target = "pricePromotion")
    @Mapping(source = "altura", target = "cubing.altura")
    @Mapping(source = "largura", target = "cubing.largura")
    @Mapping(source = "comprimento", target = "cubing.comprimento")
    @Mapping(source = "diametro", target = "cubing.diametro")
    ProdutoDTO map(Produto entity);
}
