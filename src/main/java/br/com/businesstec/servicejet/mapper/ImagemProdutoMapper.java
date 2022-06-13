package br.com.businesstec.servicejet.mapper;

import br.com.businesstec.model.entities.ImagemProduto;
import br.com.businesstec.servicejet.client.dto.ImagemProdutoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImagemProdutoMapper {


    ImagemProdutoMapper INSTANCE = Mappers.getMapper(ImagemProdutoMapper.class);

    @Mapping(source = "identificadorOrigemProduto", target = "externalId")
    @Mapping(source = "identificadorOrigem", target = "externalIdImage")
    @Mapping(source = "nomeImagem", target = "imageName")
    @Mapping(source = "imagem", target = "imageBase64Data")
    ImagemProdutoDTO map(ImagemProduto entity);


}

