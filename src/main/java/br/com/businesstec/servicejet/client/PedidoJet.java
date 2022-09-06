package br.com.businesstec.servicejet.client;

import br.com.businesstec.servicejet.client.config.FeignConfig;
import br.com.businesstec.servicejet.client.dto.AtualizaStatusPedidoDTO;
import br.com.businesstec.servicejet.client.dto.IdFilaDTO;
import br.com.businesstec.servicejet.client.dto.PedidoDTO;
import br.com.businesstec.servicejet.client.dto.Queue;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "pedido", url = "${jet.pedido.host}", configuration = FeignConfig.class)
public interface PedidoJet {

    @GetMapping(name = "getPedidos", path = "/adm_order/GetQueue")
    ResponseEntity<List<Queue<PedidoDTO>>> getPedidos(@RequestHeader("Authorization") String accessToken);

    @PostMapping(name = "atualizaStatusPedido", path="/adm_orderstatus/SetOrderStatus")
    ResponseEntity<String> atualizaStatusPedido(@RequestHeader("Authorization") String accessToken, @RequestBody AtualizaStatusPedidoDTO atualizaStatusPedidoDTO);

    @PostMapping(name = "excluirPedidoFila", path="/adm_order/DeleteQueue")
    ResponseEntity<String> deletarPedidoFila(@RequestHeader("Authorization") String accessToken, @RequestBody List<IdFilaDTO> filas);

    @GetMapping(name = "getPedidoByIdOrder", path = "/adm_order/GetOrder/{idOrder}")
    ResponseEntity<PedidoDTO> getPedidoByOrder(@RequestHeader("Authorization") String accessToken, @PathVariable("idOrder") Long idOrder);
}
