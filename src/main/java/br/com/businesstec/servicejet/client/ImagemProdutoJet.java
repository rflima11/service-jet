package br.com.businesstec.servicejet.client;

import br.com.businesstec.servicejet.client.config.FeignConfig;
import br.com.businesstec.servicejet.client.dto.ImagemProdutoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "imagemProduto", url = "${jet.produto.host}", configuration = FeignConfig.class)
public interface ImagemProdutoJet {

    @PostMapping(name = "atualizarImagem", path = "/adm_productsimage/PostImageProduct")
    ResponseEntity<String> atualizarImagem(@RequestHeader("Authorization") String accessToken, @RequestBody ImagemProdutoDTO imagemProdutoDTO);
}
