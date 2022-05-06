package br.com.businesstec.servicejet.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarcaDTO extends EntityDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("featured")
    private boolean featured;

    @JsonProperty("active")
    private boolean active;

    @JsonProperty("externalId")
    private String externalId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}
