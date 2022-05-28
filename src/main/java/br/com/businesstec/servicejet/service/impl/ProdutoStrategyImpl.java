package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ControleExecucaoFluxoEntidade;
import br.com.businesstec.servicejet.client.ProdutoJet;
import br.com.businesstec.servicejet.client.dto.BrandDTO;
import br.com.businesstec.servicejet.client.dto.CategoriaDTO;
import br.com.businesstec.servicejet.client.dto.ProdutoDTO;
import br.com.businesstec.servicejet.client.dto.Queue;
import br.com.businesstec.servicejet.config.JetProperties;
import br.com.businesstec.servicejet.enums.EnumIntegracaoStrategy;
import br.com.businesstec.servicejet.mapper.ProdutoMapper;
import br.com.businesstec.servicejet.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class ProdutoStrategyImpl implements IntegracaoStrategy {

    private static final String MENSAGEM_ERRO_PRODUTO_JA_CADASTRADO = "Houve um problema ao salvar o registro de produto, Código Externo informado, já cadastrado";

    private final ProdutoService produtoService;
    private final ProdutoMapper produtoMapper;
    private final ProdutoEcommerceService produtoEcommerceService;
    private final ProdutoJet produtoJet;
    private final JetProperties jetProperties;
    private final TokenService tokenService;
    private final ObjectMapper objectMapper;
    private final ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService;
    private final CategoriaProdutoService categoriaProdutoService;

    public ProdutoStrategyImpl(ProdutoService produtoService, ProdutoEcommerceService produtoEcommerceService, ProdutoJet produtoJet, JetProperties jetProperties, TokenService tokenService, ObjectMapper objectMapper, ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService, CategoriaProdutoService categoriaProdutoService) {
        this.produtoService = produtoService;
        this.produtoEcommerceService = produtoEcommerceService;
        this.produtoJet = produtoJet;
        this.jetProperties = jetProperties;
        this.tokenService = tokenService;
        this.objectMapper = objectMapper;
        this.controleExecucaoFluxoEntidadeService = controleExecucaoFluxoEntidadeService;
        this.categoriaProdutoService = categoriaProdutoService;
        this.produtoMapper = ProdutoMapper.INSTANCE;
    }

    @Override
//    @Retryable(FeignException.class)
    public void executar(ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        try {
            var produtoSalvo = produtoService.recuperarProdutoNaoIntegradoByIdEntidade(controleExecucaoFluxoEntidade.getIdEntidade());
            var produtoDto = produtoMapper.map(produtoSalvo);
            var produtoEcommerce = produtoEcommerceService.findByIdProduto(produtoSalvo.getId());
            produtoDto.setPromotionStore(produtoEcommerce.getPromocaoLoja());
            produtoDto.setCategories(categoriaProdutoService.recuperarCategorias(Long.valueOf(produtoDto.getExternalId())));
            produtoDto.setBrand(new BrandDTO());
            produtoDto.setActive(produtoEcommerce.isAtivo());
            var accessToken = tokenService.getAccessToken(jetProperties.getProduto());
            var filaProdutos = produtoJet.getFilaProduto(accessToken).getBody();
            Thread.sleep(300);

            if (verificarSeProdutoFoiIntegrado(filaProdutos, produtoDto)) {
                produtoJet.atualizarProduto(accessToken, produtoDto);
                Thread.sleep(300);
            } else {
                produtoJet.adicionarNovoProdutoJet(accessToken, produtoDto);
                Thread.sleep(300);

            }
            controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);
            System.out.println(produtoDto);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

//    @Recover
//    private void recover(FeignException e) throws JsonProcessingException {
//        if (e.contentUTF8().contains(MENSAGEM_ERRO_PRODUTO_JA_CADASTRADO)) {
//            var body = new String(e.request().body(), StandardCharsets.US_ASCII);
//            var accessToken = tokenService.getAccessToken(jetProperties.getProduto());
//            var produtoDto = objectMapper.readValue(body, ProdutoDTO.class);
//            var produto = produtoService.encontrarProdutoPeloIdentificadorOrigem(produtoDto.getExternalId());
//            var controleExecucaoFluxoEntidade = controleExecucaoFluxoEntidadeService.encontrarFluxoExecucaoEntidadeByIdEntidade(produto.getIdEntidade());
//            produtoJet.atualizarProduto(accessToken, objectMapper.readValue(body, ProdutoDTO.class));
//            controleExecucaoFluxoEntidadeService.atualizarIntegracao(controleExecucaoFluxoEntidade);
//
//        }
//    }

    private boolean verificarSeProdutoFoiIntegrado(List<Queue<ProdutoDTO>> produtos, ProdutoDTO produtoDTO) {
        var optProdutoSalvo = produtos.stream().filter(x -> {
            var produto = x.getEntity();
            return Objects.equals(produto.getExternalId(), produtoDTO.getExternalId());
        }).findFirst();
        if (optProdutoSalvo.isPresent()) {
            produtoDTO.setIdProduct(optProdutoSalvo.get().getEntity().getIdProduct());
            return true;
        }
        return false;
    }

    private List<CategoriaDTO> mockCategoria() {
        var categoriaDto = new CategoriaDTO();
        categoriaDto.setName(" ");
        categoriaDto.setActive(true);
        categoriaDto.setExternalId("string");
        return Collections.singletonList(categoriaDto);
    }



    @Override
    public EnumIntegracaoStrategy getIntegracaoStrategy() {
        return EnumIntegracaoStrategy.PRODUTO_STRATEGY;
    }

}
