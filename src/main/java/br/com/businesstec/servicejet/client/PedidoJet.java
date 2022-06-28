package br.com.businesstec.servicejet.client;

import br.com.businesstec.servicejet.client.config.FeignConfig;
import br.com.businesstec.servicejet.client.dto.PedidoDTO;
import br.com.businesstec.servicejet.client.dto.Queue;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "pedido", url = "${jet.pedido.host}", configuration = FeignConfig.class)
public interface PedidoJet {

    @GetMapping(name = "getPedidos", path = "/adm_order/GetQueue")
    ResponseEntity<List<Queue<PedidoDTO>>> getPedidos(@RequestHeader("Authorization") String accessToken);

}
