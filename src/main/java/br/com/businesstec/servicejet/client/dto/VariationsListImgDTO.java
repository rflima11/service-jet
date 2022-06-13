package br.com.businesstec.servicejet.client.dto;

import java.util.List;

public class VariationsListImgDTO {

    private String variationListType;
    private List<String> variationListIds;

    public VariationsListImgDTO() {
        variationListType = "ExternalID";
    }

    public String getVariationListType() {
        return variationListType;
    }

    public void setVariationListType(String variationListType) {
        this.variationListType = variationListType;
    }

    public List<String> getVariationListIds() {
        return variationListIds;
    }

    public void setVariationListIds(List<String> variationListIds) {
        this.variationListIds = variationListIds;
    }


}
