package com.example.eshopping.service;

import com.example.eshopping.model.cashfree.TransactionChecksumRequest;
import com.example.eshopping.model.cashfree.TransactionChecksumResponse;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface CashFreeService {

    TransactionChecksumResponse generateTransactionChecksum(TransactionChecksumRequest transactionChecksumRequest) throws NoSuchAlgorithmException, InvalidKeyException;

}
