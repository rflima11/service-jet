package br.com.businesstec.servicejet.client.dto;

import java.math.BigDecimal;

public class ProdutoDTO {

    private String code;
    private String name;
    private String promotionStore;
    private Boolean flagExhausted;
    private String imageHome;
    private String stock;
    private BigDecimal price;
    private String externalId;
    private String active;
    private CategoriaDTO categories;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
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

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public CategoriaDTO getCategories() {
        return categories;
    }

    public void setCategories(CategoriaDTO categories) {
        this.categories = categories;
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
