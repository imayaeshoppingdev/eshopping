package com.example.eshopping.client.paytm;

import com.example.eshopping.client.paytm.request.PaytmInitiateTransactionRequest;

public interface PaytmClient {

    String initiateTransaction(PaytmInitiateTransactionRequest paytmInitiateTransactionRequest);
}
