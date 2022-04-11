package br.com.businesstec.servicejet.client;

import br.com.businesstec.servicejet.client.dto.ClienteDTO;
import br.com.businesstec.servicejet.client.dto.Queue;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "cliente", url = "${jet.pedido.host}", configuration = FeignConfig.class)
public interface ClienteJet {

    @GetMapping(name = "getClientes", path = "/adm_customer/GetQueue")
    ResponseEntity<List<Queue<ClienteDTO>>> getClientes(@RequestHeader("Authorization") String accessToken);
}
