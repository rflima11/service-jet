package br.com.businesstec.servicejet.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoriaDTO {

    private Long idCategory;
    @JsonProperty("default")
    private Boolean defaultt;
    private String externalId;

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public Boolean getDefaultt() {
        return defaultt;
    }

    public void setDefaultt(Boolean defaultt) {
        this.defaultt = defaultt;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}
