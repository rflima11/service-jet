package br.com.businesstec.servicejet.client;

import br.com.businesstec.servicejet.client.config.FeignConfig;
import br.com.businesstec.servicejet.client.dto.ProductPriceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "precoProduto", url = "${jet.produto.host}", configuration = FeignConfig.class)
public interface PrecoProdutoJet {

    @PutMapping(name = "atualizarPreco", path = "/adm_prices")
    ResponseEntity<String> atualizarPreco(@RequestHeader("Authorization") String accessToken, @RequestBody ProductPriceDTO produtoPriceDTO);

}
