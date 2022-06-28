package br.com.businesstec.servicejet.client;

import br.com.businesstec.servicejet.client.config.FeignConfig;
import br.com.businesstec.servicejet.client.dto.ClienteDTO;
import br.com.businesstec.servicejet.client.dto.IdFilaDTO;
import br.com.businesstec.servicejet.client.dto.Queue;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "cliente", url = "${jet.pedido.host}", configuration = FeignConfig.class)
public interface ClienteJet {

    @GetMapping(name = "getClientes", path = "/adm_customer/GetQueue")
    ResponseEntity<List<Queue<ClienteDTO>>> getClientes(@RequestHeader("Authorization") String accessToken);

    @PostMapping(name = "deletarFila", path = "/adm_customer/DeleteQueue")
    ResponseEntity<String> deletarFila(@RequestHeader("Authorization") String accessToken, @RequestBody List<IdFilaDTO> filas);

}
