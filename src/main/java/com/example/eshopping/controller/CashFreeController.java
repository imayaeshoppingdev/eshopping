package com.example.eshopping.controller;

import com.example.eshopping.model.cashfree.TransactionChecksumRequest;
import com.example.eshopping.model.cashfree.TransactionChecksumResponse;
import com.example.eshopping.service.CashFreeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/cashfree")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class CashFreeController {

    CashFreeService cashFreeService;

    public CashFreeController(CashFreeService cashFreeService) {
        this.cashFreeService = cashFreeService;
    }

    @PostMapping("/transaction/checksum")
    public ResponseEntity<TransactionChecksumResponse> transactionChecksum(@RequestBody TransactionChecksumRequest transactionChecksumRequest) throws NoSuchAlgorithmException, InvalidKeyException {
        //TODO Validate request
        try {
            TransactionChecksumResponse transactionChecksumResponse = cashFreeService.generateTransactionChecksum(transactionChecksumRequest);
            return ResponseEntity.ok(transactionChecksumResponse);
        }
        catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}


