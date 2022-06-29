package br.com.businesstec.servicejet.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VariationSkuDTO {

    private Long idVariation;
    private String externalId;

    public VariationSkuDTO() {}

    public VariationSkuDTO(String externalId) {
        this.externalId = externalId;
    }
    public Long getIdVariation() {
        return idVariation;
    }

    public void setIdVariation(Long idVariation) {
        this.idVariation = idVariation;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}
