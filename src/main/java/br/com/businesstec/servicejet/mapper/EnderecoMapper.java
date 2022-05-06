package br.com.businesstec.servicejet.mapper;

import br.com.businesstec.model.entities.Endereco;
import br.com.businesstec.servicejet.client.dto.AddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EnderecoMapper {

    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    @Mapping(source = "idAddress", target = "identificadorOrigem")
    @Mapping(source = "streetAddress", target = "logradouro")
    @Mapping(source = "number", target = "numero")
    @Mapping(source = "complement", target = "complemento")
    @Mapping(source = "neighbourhood", target = "bairro")
    @Mapping(source = "city", target = "cidade")
    @Mapping(source = "state", target = "estado")
    @Mapping(source = "zipCode", target = "cep")
    @Mapping(source = "country", target = "pais")
    @Mapping(source = "referencePoint", target = "pontoReferencia")
    @Mapping(source = "addressType", target = "tipoEndereco")
    Endereco map(AddressDTO dto);
}
