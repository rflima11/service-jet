package br.com.businesstec.servicejet.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderPaymentDTO {

    private LocalDateTime dateRegister;
    private String linkPayment;
    private int idPaymentGateway;
    private String paymentCode;
    private String transactionCode;
    private String transactionStatus;
    private String tid;
    private String nsu;
    private String brand;
    private int idPreOrder;

    public LocalDateTime getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(LocalDateTime dateRegister) {
        this.dateRegister = dateRegister;
    }

    public String getLinkPayment() {
        return linkPayment;
    }

    public void setLinkPayment(String linkPayment) {
        this.linkPayment = linkPayment;
    }

    public int getIdPaymentGateway() {
        return idPaymentGateway;
    }

    public void setIdPaymentGateway(int idPaymentGateway) {
        this.idPaymentGateway = idPaymentGateway;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getNsu() {
        return nsu;
    }

    public void setNsu(String nsu) {
        this.nsu = nsu;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getIdPreOrder() {
        return idPreOrder;
    }

    public void setIdPreOrder(int idPreOrder) {
        this.idPreOrder = idPreOrder;
    }
}
