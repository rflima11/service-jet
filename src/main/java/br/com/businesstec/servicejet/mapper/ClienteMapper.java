package br.com.businesstec.servicejet.mapper;

import br.com.businesstec.model.entities.Cliente;
import br.com.businesstec.servicejet.client.dto.ClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    @Mapping(source = "name", target = "nome")
    @Mapping(source = "rg", target = "rgIe")
    @Mapping(source = "contact", target = "contato")
    @Mapping(source = "typeCustomer", target = "tipoPessoa")

    @Mapping(source = "gender", target = "sexo")
    @Mapping(source = "dateBirth", target = "dataNascimento")
    @Mapping(source = "idCustomer", target = "identificadorOrigem")
    @Mapping(source = "phone.phone1", target = "telefone1")
    @Mapping(source = "phone.phone2", target = "telefone2")
    @Mapping(source = "phone.phoneAdditional", target = "telefone3")
    Cliente map(ClienteDTO dto);


    default String map(LocalDateTime date) {
        var formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME.ofPattern("yyyyMMdd HH:mm:ss").withZone(ZoneId.systemDefault());
        return formatter.format(date);
    }

}
