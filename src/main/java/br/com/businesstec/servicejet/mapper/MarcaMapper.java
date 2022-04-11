package br.com.businesstec.servicejet.mapper;

import br.com.businesstec.servicejet.client.dto.MarcaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MarcaMapper {

    MarcaMapper INSTANCE = Mappers.getMapper(MarcaMapper.class);

    @Mapping(source = "nome", target = "name")
    @Mapping(source = "emDestaque", target = "featured")
    @Mapping(source = "ativo", target = "active")
    MarcaDTO map(br.com.businesstec.servicejet.model.Marca entity);

}
