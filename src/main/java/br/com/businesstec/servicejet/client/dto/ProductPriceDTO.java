package br.com.businesstec.servicejet.client.dto;

import java.math.BigDecimal;

public class ProductPriceDTO {

    private Long idProduct;
    private BigDecimal price;
    private BigDecimal pricePromotion;
    private String externalId;
    private ProductSkuPriceDTO skuPrice;

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPricePromotion() {
        return pricePromotion;
    }

    public void setPricePromotion(BigDecimal pricePromotion) {
        this.pricePromotion = pricePromotion;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public ProductSkuPriceDTO getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(ProductSkuPriceDTO skuPrice) {
        this.skuPrice = skuPrice;
    }
}
