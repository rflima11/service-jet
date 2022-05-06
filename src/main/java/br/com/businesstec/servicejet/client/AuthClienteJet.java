package br.com.businesstec.servicejet.client;

import br.com.businesstec.servicejet.client.config.FeignConfig;
import br.com.businesstec.servicejet.client.dto.CredentialsDTO;
import br.com.businesstec.servicejet.client.dto.TokenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authCliente", url = "${jet.pedido.host}")
public interface AuthClienteJet {

    @PostMapping("/auth")
    ResponseEntity<TokenDTO> auth(@RequestBody CredentialsDTO credentialsDTO);

}
