package br.com.businesstec.servicejet.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VariacaoDTO extends EntityDTO {

    @JsonProperty("idReference")
    private Long idReference;

    @JsonProperty("name")
    private String name;
    @JsonProperty("externalId")
    private String externalId;
    @JsonProperty("variations")
    private List<VariationsDTO> variations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public List<VariationsDTO> getVariations() {
        return variations;
    }

    public void setVariations(List<VariationsDTO> variations) {
        this.variations = variations;
    }

    public Long getIdReference() {
        return idReference;
    }

    public void setIdReference(Long idReference) {
        this.idReference = idReference;
    }
}
