package br.com.businesstec.servicejet.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoSkuDTO {

    private String skuCode;
    private BigDecimal price;
    private Long stock;
    private Boolean standard;
    private PesoDTO cubing;
    private BigDecimal pricePromotion;
    private Boolean visible;
    private String externalId;
    private Boolean active;
    private List<VariationSkuDTO> variations;

    public ProdutoSkuDTO() {
        variations = new ArrayList<>();
    }

    public void adicionarVariacao(VariationSkuDTO variationSkuDTO) {
        variations.add(variationSkuDTO);
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Boolean getStandard() {
        return standard;
    }

    public void setStandard(Boolean standard) {
        this.standard = standard;
    }

    public PesoDTO getCubing() {
        return cubing;
    }

    public void setCubing(PesoDTO cubing) {
        this.cubing = cubing;
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

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<VariationSkuDTO> getVariations() {
        return variations;
    }

    public void setVariations(List<VariationSkuDTO> variations) {
        this.variations = variations;
    }
}
