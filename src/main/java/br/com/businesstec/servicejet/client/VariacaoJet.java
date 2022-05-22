package br.com.businesstec.servicejet.client;

import br.com.businesstec.servicejet.client.config.FeignConfig;
import br.com.businesstec.servicejet.client.dto.Queue;
import br.com.businesstec.servicejet.client.dto.VariacaoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "variacao", url = "${jet.produto.host}", configuration = FeignConfig.class)
public interface VariacaoJet {

    @PostMapping(name = "adicionarVariacao", path = "/adm_variations")
    ResponseEntity<String> adicionarNovaVariacao(@RequestHeader("Authorization") String accessToken, @RequestBody VariacaoDTO produtoDTO);

    @PutMapping(name = "atualizarVariacao", path = "/adm_variations")
    ResponseEntity<String> atualizarVariacao(@RequestHeader("Authorization") String accessToken, @RequestBody VariacaoDTO produtoDTO);

    @GetMapping(name = "filaVariacoes", path = "/adm_variations/GetQueue")
    ResponseEntity<List<Queue<VariacaoDTO>>> getVariacoes(@RequestHeader("Authorization") String accessToken);
}
