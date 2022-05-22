package br.com.businesstec.servicejet.mapper;

import br.com.businesstec.model.entities.Variacao;
import br.com.businesstec.model.entities.VariacaoItem;
import br.com.businesstec.servicejet.client.dto.VariacaoDTO;
import br.com.businesstec.servicejet.client.dto.VariationsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VariacaoItemMapper {

    VariacaoItemMapper INSTANCE = Mappers.getMapper(VariacaoItemMapper.class);

    @Mapping(source = "descricao", target = "name")
    @Mapping(source = "identificadorOrigem", target = "externalId")
    VariationsDTO map(VariacaoItem entity);
}
