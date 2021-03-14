package com.example.eshopping.model.paytm;

public class InitiateTransactionRequest {

    private String orderId;

    private Double amount;

    public String getOrderId() {
        return orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
