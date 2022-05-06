package br.com.businesstec.servicejet.client.dto;

public class AtualizarEstoqueDTO {

    private Long idProduct;
    private Long stock;
    private Long externalId;
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

    public Long getExternalId() {
        return externalId;
    }

    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }

    public SkuStockDTO getSkuStock() {
        return skuStock;
    }

    public void setSkuStock(SkuStockDTO skuStock) {
        this.skuStock = skuStock;
    }
}
