package br.com.businesstec.servicejet.client;

import br.com.businesstec.servicejet.client.config.FeignConfig;
import br.com.businesstec.servicejet.client.dto.MarcaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "marca", url = "${jet.produto.host}", configuration = FeignConfig.class)
public interface MarcaClient {

    @PostMapping(name = "inserirMarca", path = "/adm_brands")
    ResponseEntity<String> inserirMarca(@RequestHeader("Authorization") String accessToken, @RequestBody MarcaDTO marcaDTO);

    @PutMapping(name = "atualizarMarca", path = "/adm_brands")
    ResponseEntity<String> atualizarMarca(@RequestHeader("Authorization") String accessToken, @RequestBody MarcaDTO marcaDTO);

}
