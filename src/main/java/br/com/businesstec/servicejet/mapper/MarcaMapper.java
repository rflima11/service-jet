package br.com.businesstec.servicejet.mapper;

import br.com.businesstec.model.entities.Marca;
import br.com.businesstec.servicejet.client.dto.MarcaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MarcaMapper {

    MarcaMapper INSTANCE = Mappers.getMapper(MarcaMapper.class);

    @Mapping(source = "descricao", target = "name")
    @Mapping(source = "identificadorOrigem", target = "externalId")
    MarcaDTO map(Marca entity);

}
