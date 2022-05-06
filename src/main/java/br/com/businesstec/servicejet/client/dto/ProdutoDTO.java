package br.com.businesstec.servicejet.client.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoDTO {

    private String code;
    private String name;
    private String promotionStore;
    private Boolean flagExhausted;
    private BigDecimal pricePromotion;
    private BrandDTO brand;
    private String imageHome;
    private Long stock;
    private BigDecimal price;
    private String externalId;
    private Boolean active;
    private List<CategoriaDTO> categories;
    private PesoDTO cubing;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BrandDTO getBrand() {
        return brand;
    }

    public void setBrand(BrandDTO brand) {
        this.brand = brand;
    }

    public BigDecimal getPricePromotion() {
        return pricePromotion;
    }

    public void setPricePromotion(BigDecimal pricePromotion) {
        this.pricePromotion = pricePromotion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPromotionStore() {
        return promotionStore;
    }

    public void setPromotionStore(String promotionStore) {
        this.promotionStore = promotionStore;
    }

    public Boolean getFlagExhausted() {
        return flagExhausted;
    }

    public void setFlagExhausted(Boolean flagExhausted) {
        this.flagExhausted = flagExhausted;
    }

    public String getImageHome() {
        return imageHome;
    }

    public void setImageHome(String imageHome) {
        this.imageHome = imageHome;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }

    public List<CategoriaDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriaDTO> categories) {
        this.categories = categories;
    }

    public PesoDTO getCubing() {
        return cubing;
    }

    public void setCubing(PesoDTO cubing) {
        this.cubing = cubing;
    }
}


//{
//        "categories": {
//        "idCategory": 1,
//        "default": true,
//        "externalId": "12"
//        },
//        "brand": {
//        "idBrand": 1,
//        "externalId": "1"
//        },
//        "flagShipping": false,
//        "code": "1231",
//        "name": "edqwewq",
//        "promotionStore": "ewq",
//        "flagExhausted": true,
//        "imageHome": "ewqewq",
//        "stock": "ewqeqw",
//        "price": 32131,
//        "externalId": "1",
//        "active": "weq"
//        }
