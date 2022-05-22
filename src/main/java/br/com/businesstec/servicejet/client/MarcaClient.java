package br.com.businesstec.servicejet.client;

import br.com.businesstec.model.entities.Marca;
import br.com.businesstec.servicejet.client.config.FeignConfig;
import br.com.businesstec.servicejet.client.dto.ClienteDTO;
import br.com.businesstec.servicejet.client.dto.MarcaDTO;
import br.com.businesstec.servicejet.client.dto.Queue;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "marca", url = "${jet.produto.host}", configuration = FeignConfig.class)
public interface MarcaClient {

    @PostMapping(name = "inserirMarca", path = "/adm_brands")
    ResponseEntity<String> inserirMarca(@RequestHeader("Authorization") String accessToken, @RequestBody MarcaDTO marcaDTO);

    @PutMapping(name = "atualizarMarca", path = "/adm_brands")
    ResponseEntity<String> atualizarMarca(@RequestHeader("Authorization") String accessToken, @RequestBody MarcaDTO marcaDTO);

    @GetMapping(name = "filaMarcas", path = "/adm_brands/GetQueue")
    ResponseEntity<List<Queue<MarcaDTO>>> getMarcas(@RequestHeader("Authorization") String accessToken);

    @PostMapping(name = "listaIdentificacoesMarcas", path = "/adm_brands/externalID")
    ResponseEntity<List<MarcaDTO>> getIdentificadorMarca(@RequestHeader("Authorization") String accessToken, @RequestBody List<Long> externalIds);


}
