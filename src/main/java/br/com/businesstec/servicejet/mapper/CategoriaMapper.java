package br.com.businesstec.servicejet.mapper;

import br.com.businesstec.model.entities.Categoria;
import br.com.businesstec.model.entities.Cliente;
import br.com.businesstec.servicejet.client.dto.CategoriaDTO;
import br.com.businesstec.servicejet.client.dto.ClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoriaMapper {

    CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

    @Mapping(source = "identificadorOrigem", target = "externalId")
    @Mapping(source = "descricao", target = "name")
    @Mapping(source = "nivelSuperior", target = "level")
    CategoriaDTO map(Categoria entity);


}
