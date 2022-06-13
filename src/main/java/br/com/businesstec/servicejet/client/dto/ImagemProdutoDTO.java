package br.com.businesstec.servicejet.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImagemProdutoDTO {

    private String externalId;
    @JsonProperty("isPrincipal")
    private Boolean principal;
    @JsonProperty("isAlternative")
    private Boolean alternativo;
    private String externalIdImage;
    private String imageName;
    private VariationsListImgDTO variationList;
    private String imageBase64Data;

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public Boolean getAlternativo() {
        return alternativo;
    }

    public void setAlternativo(Boolean alternativo) {
        this.alternativo = alternativo;
    }

    public VariationsListImgDTO getVariationList() {
        return variationList;
    }

    public void setVariationList(VariationsListImgDTO variationList) {
        this.variationList = variationList;
    }

    public String getExternalIdImage() {
        return externalIdImage;
    }

    public void setExternalIdImage(String externalIdImage) {
        this.externalIdImage = externalIdImage;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageBase64Data() {
        return imageBase64Data;
    }

    public void setImageBase64Data(String imageBase64Data) {
        this.imageBase64Data = imageBase64Data;
    }


}
