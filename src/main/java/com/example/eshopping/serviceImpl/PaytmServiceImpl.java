package com.example.eshopping.serviceImpl;

import com.example.eshopping.client.paytm.PaytmClient;
import com.example.eshopping.client.paytm.request.PaytmInitiateTransactionRequest;
import com.example.eshopping.model.paytm.InitiateTransactionRequest;
import com.example.eshopping.model.paytm.InitiateTransactionResponse;
import com.example.eshopping.service.PaytmService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;


@Service
public class PaytmServiceImpl implements PaytmService {

    private PaytmClient paytmClient;

    public PaytmServiceImpl(PaytmClient paytmClient) {
        this.paytmClient = paytmClient;
    }

    public InitiateTransactionResponse initiateTransaction(InitiateTransactionRequest initiateTransactionRequest) throws JsonProcessingException {
        PaytmInitiateTransactionRequest paytmInitiateTransactionRequest = new PaytmInitiateTransactionRequest();
        paytmInitiateTransactionRequest.setAmount(initiateTransactionRequest.getAmount());
        paytmInitiateTransactionRequest.setOrderId(initiateTransactionRequest.getOrderId());
        String responseData = paytmClient.initiateTransaction(paytmInitiateTransactionRequest);
        return new ObjectMapper().readValue(responseData, InitiateTransactionResponse.class);
    }

}
