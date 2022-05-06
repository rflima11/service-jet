package br.com.businesstec.servicejet.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoryListOrderStatusDTO {

    private int idOrderStatus;
    private String statusCode;
    private String statusName;
    private LocalDateTime dateRegisterStatus;
    private String message;
    private String icon;
    private boolean sendEmail;
    private OrderInfoDTO orderInfo;

    public int getIdOrderStatus() {
        return idOrderStatus;
    }

    public void setIdOrderStatus(int idOrderStatus) {
        this.idOrderStatus = idOrderStatus;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public LocalDateTime getDateRegisterStatus() {
        return dateRegisterStatus;
    }

    public void setDateRegisterStatus(LocalDateTime dateRegisterStatus) {
        this.dateRegisterStatus = dateRegisterStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    public OrderInfoDTO getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoDTO orderInfo) {
        this.orderInfo = orderInfo;
    }
}
