package br.com.businesstec.servicejet.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Base64;

public class CredentialsDTO {

    private String host;
    private String username;
    private String password;
    private String storeId;

    @JsonProperty("userName")
    public String getUsernameBase64() {
        return Base64.getEncoder().encodeToString(username.getBytes());
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("password")
    public String getPasswordBase64() {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("storeId")
    public String getStoreId() {
        return Base64.getEncoder().encodeToString(storeId.getBytes());
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
