package br.com.businesstec.servicejet.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AtualizarEstoqueDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long idProduct;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long stock;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String externalId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SkuStockDTO skuStock;

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public SkuStockDTO getSkuStock() {
        return skuStock;
    }

    public void setSkuStock(SkuStockDTO skuStock) {
        this.skuStock = skuStock;
    }
}
