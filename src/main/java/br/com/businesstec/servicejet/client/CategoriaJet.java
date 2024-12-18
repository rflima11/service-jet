package br.com.businesstec.servicejet.client;

import br.com.businesstec.servicejet.client.config.FeignConfig;
import br.com.businesstec.servicejet.client.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "categoria", url = "${jet.produto.host}", configuration = FeignConfig.class)
public interface CategoriaJet {

    @PostMapping(name = "adicionarNovaCategoria", path = "/adm_categories")
    ResponseEntity<String> adicionarNovaCategoria(@RequestHeader("Authorization") String accessToken, @RequestBody CategoriaDTO produtoDTO);

    @GetMapping(name = "getFilaCategorias", path = "/adm_categories/GetQueue")
    ResponseEntity<List<Queue<CategoriaDTO>>> getCategorias(@RequestHeader("Authorization") String accessToken);

    @PutMapping(name = "atualizarCategoria", path = "/adm_categories")
    ResponseEntity<String> atualizarCategoria(@RequestHeader("Authorization") String accessToken, @RequestBody CategoriaDTO produtoDTO);

    @GetMapping(name = "getLevelCategoria", path = "/adm_categories/GetLevel")
    ResponseEntity<LevelDTO> getLevelCategoria(@RequestHeader("Authorization") String accessToken, @RequestParam("externalID") String externalId);
}
