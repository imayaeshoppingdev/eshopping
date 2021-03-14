package com.example.eshopping.model.paytm;

public class Head {

    private String responseTimestamp;
    private String version;
    private String signature;

    public String getResponseTimestamp() {
        return responseTimestamp;
    }

    public String getVersion() {
        return version;
    }

    public String getSignature() {
        return signature;
    }

    public void setResponseTimestamp(String responseTimestamp) {
        this.responseTimestamp = responseTimestamp;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}

