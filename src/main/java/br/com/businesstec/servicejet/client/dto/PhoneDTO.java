package br.com.businesstec.servicejet.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneDTO {

    private String phone1;
    private String phone2;
    private String phoneAdditional;

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhoneAdditional() {
        return phoneAdditional;
    }

    public void setPhoneAdditional(String phoneAdditional) {
        this.phoneAdditional = phoneAdditional;
    }
}
