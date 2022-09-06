package br.com.businesstec.servicejet.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoDTO extends EntityDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long idProduct;
    private String code;
    private String name;
    private String promotionStore;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean flagExhausted;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ProdutoSkuDTO> skUs;
    private BigDecimal pricePromotion;
    private BrandDTO brand;
    private String imageHome;
    private Long stock;
    private BigDecimal price;
    private String externalId;
    private Integer crossDocking;
    private Boolean active;
    private List<CategoriaDTO> categories;
    private PesoDTO cubing;
    private String descriptionDetail;
    private String descriptionDetailSummary;


    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

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

    public List<ProdutoSkuDTO> getSkUs() {
        return skUs;
    }

    public void setSkUs(List<ProdutoSkuDTO> skUs) {
        this.skUs = skUs;
    }

    public String getDescriptionDetail() {
        return descriptionDetail;
    }

    public void setDescriptionDetail(String descriptionDetail) {
        this.descriptionDetail = descriptionDetail;
    }

    public String getDescriptionDetailSummary() {
        return descriptionDetailSummary;
    }

    public void setDescriptionDetailSummary(String descriptionDetailSummary) {
        this.descriptionDetailSummary = descriptionDetailSummary;
    }

    public Integer getCrossDocking() {
        return crossDocking;
    }

    public void setCrossDocking(Integer crossDocking) {
        this.crossDocking = crossDocking;
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
