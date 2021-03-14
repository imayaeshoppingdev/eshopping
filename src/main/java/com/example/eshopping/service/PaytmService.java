package com.example.eshopping.service;

import com.example.eshopping.model.paytm.InitiateTransactionRequest;
import com.example.eshopping.model.paytm.InitiateTransactionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface PaytmService {

    InitiateTransactionResponse initiateTransaction(InitiateTransactionRequest initiateTransactionRequest) throws JsonProcessingException;

}
