package br.com.businesstec.servicejet.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SkuStockDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long idSku;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double stock;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String externalId;

    public Long getIdSku() {
        return idSku;
    }

    public void setIdSku(Long idSku) {
        this.idSku = idSku;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}
