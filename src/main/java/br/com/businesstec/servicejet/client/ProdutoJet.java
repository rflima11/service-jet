package br.com.businesstec.servicejet.client;

import br.com.businesstec.servicejet.client.config.FeignConfig;
import br.com.businesstec.servicejet.client.dto.AtualizarEstoqueDTO;
import br.com.businesstec.servicejet.client.dto.ProdutoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "produto", url = "${jet.produto.host}", configuration = FeignConfig.class)
public interface ProdutoJet {

    @PostMapping(name = "adicionarProduto", path = "/adm_products")
    ResponseEntity<String> adicionarNovoProdutoJet(@RequestHeader("Authorization") String accessToken, @RequestBody ProdutoDTO produtoDTO);

    @PutMapping(name = "atualizarProduto", path = "/adm_products")
    ResponseEntity<String> atualizarProduto(@RequestHeader("Authorization") String accessToken, @RequestBody ProdutoDTO produtoDTO);

    @PutMapping(name = "atualizarEstoqueProduto", path = "/adm_stocks")
    ResponseEntity<String> atualizarEstoqueProduto(@RequestHeader("Authorization") String accessToken, @RequestBody AtualizarEstoqueDTO atualizarEstoqueDTO);
}
