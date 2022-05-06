package br.com.businesstec.servicejet.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItemDTO {

    private int idOrderItem;
    private int idProduct;
    private String productCode;
    private int idSku;
    private String name;
    private double total;
    private int quantity;
    private double unitPrice;
    private String deliveryTime;
    private String image;
    private String brand;
    private String category;
    private String externalIdProduct;
    private String externalIdSku;
    private boolean isKit;
    private String skuCode;
    private int crossDocking;
    private List<AttributeDTO> attribute;
    private String nameProduct;

    public int getIdOrderItem() {
        return idOrderItem;
    }

    public void setIdOrderItem(int idOrderItem) {
        this.idOrderItem = idOrderItem;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getIdSku() {
        return idSku;
    }

    public void setIdSku(int idSku) {
        this.idSku = idSku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExternalIdProduct() {
        return externalIdProduct;
    }

    public void setExternalIdProduct(String externalIdProduct) {
        this.externalIdProduct = externalIdProduct;
    }

    public String getExternalIdSku() {
        return externalIdSku;
    }

    public void setExternalIdSku(String externalIdSku) {
        this.externalIdSku = externalIdSku;
    }

    public boolean isKit() {
        return isKit;
    }

    public void setKit(boolean kit) {
        isKit = kit;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public int getCrossDocking() {
        return crossDocking;
    }

    public void setCrossDocking(int crossDocking) {
        this.crossDocking = crossDocking;
    }

    public List<AttributeDTO> getAttribute() {
        return attribute;
    }

    public void setAttribute(List<AttributeDTO> attribute) {
        this.attribute = attribute;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
}
