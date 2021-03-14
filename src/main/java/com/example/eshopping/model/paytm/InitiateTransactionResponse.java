package com.example.eshopping.model.paytm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InitiateTransactionResponse {

    private Head head;
    private Body body;

    public Head getHead() {
        return head;
    }

    public Body getBody() {
        return body;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public void setBody(Body body) {
        this.body = body;
    }

}
