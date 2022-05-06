package br.com.businesstec.servicejet.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderInfoDTO {

    public int idOrderInfo;

    public int getIdOrderInfo() {
        return idOrderInfo;
    }

    public void setIdOrderInfo(int idOrderInfo) {
        this.idOrderInfo = idOrderInfo;
    }
}
