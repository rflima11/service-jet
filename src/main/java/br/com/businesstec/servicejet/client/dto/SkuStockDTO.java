package br.com.businesstec.servicejet.client.dto;

public class SkuStockDTO {

    private Long idSku;
    private Long stock;
    private String externalId;

    public Long getIdSku() {
        return idSku;
    }

    public void setIdSku(Long idSku) {
        this.idSku = idSku;
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
}
