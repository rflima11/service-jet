package br.com.businesstec.servicejet.datasources.jet.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Marca {

    @JsonProperty("name")
    private String name;

    @JsonProperty("featured")
    private boolean featured;

    @JsonProperty("active")
    private boolean active;

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
}
