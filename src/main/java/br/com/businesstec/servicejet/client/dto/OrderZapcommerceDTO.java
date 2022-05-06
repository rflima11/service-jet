package br.com.businesstec.servicejet.client.dto;

public class OrderZapcommerceDTO {

    private String idOrderZap;
    private String nameSeller;
    private int idSeller;
    private String emailSeller;

    public String getIdOrderZap() {
        return idOrderZap;
    }

    public void setIdOrderZap(String idOrderZap) {
        this.idOrderZap = idOrderZap;
    }

    public String getNameSeller() {
        return nameSeller;
    }

    public void setNameSeller(String nameSeller) {
        this.nameSeller = nameSeller;
    }

    public int getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(int idSeller) {
        this.idSeller = idSeller;
    }

    public String getEmailSeller() {
        return emailSeller;
    }

    public void setEmailSeller(String emailSeller) {
        this.emailSeller = emailSeller;
    }
}
