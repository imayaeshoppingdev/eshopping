package com.example.eshopping.controller;

import com.example.eshopping.model.paytm.InitiateTransactionRequest;
import com.example.eshopping.model.paytm.InitiateTransactionResponse;
import com.example.eshopping.service.PaytmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paytm")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class PaytmController {

    PaytmService paytmService;

    public PaytmController(PaytmService paytmService) {
        this.paytmService = paytmService;
    }

    @PostMapping("/transaction/initiate")
    public ResponseEntity<InitiateTransactionResponse> initiateTransaction(@RequestBody InitiateTransactionRequest initiateTransactionRequest) {
        //TODO Validate request
        try {
            InitiateTransactionResponse initiateTransactionResponse = paytmService.initiateTransaction(initiateTransactionRequest);
            return ResponseEntity.ok(initiateTransactionResponse);
        }
        catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}


