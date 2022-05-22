package br.com.businesstec.servicejet.client;

import br.com.businesstec.servicejet.client.config.FeignConfig;
import br.com.businesstec.servicejet.client.dto.ProdutoDTO;
import br.com.businesstec.servicejet.client.dto.ProdutoSkuDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "produtoSku", url = "${jet.produto.host}", configuration = FeignConfig.class)
public interface ProdutoSkuJet {

    @PostMapping(name = "adicionarProdutoSku", path = "/adm_sku")
    ResponseEntity<String> adicionarNovoProdutoSku(@RequestHeader("Authorization") String accessToken, @RequestParam("IdProduct") Long idProduto, @RequestBody ProdutoSkuDTO produtoDTO);

    @PutMapping(name = "atualizarProdutoSku", path = "/adm_sku")
    ResponseEntity<String> atualizarProdutoSku(@RequestHeader("Authorization") String accessToken, @RequestParam("IdProduct") Long idProduto, @RequestBody ProdutoSkuDTO produtoDTO);
}
