package com.example.eshopping.serviceImpl;

import com.example.eshopping.model.cashfree.TransactionChecksumRequest;
import com.example.eshopping.model.cashfree.TransactionChecksumResponse;
import com.example.eshopping.service.CashFreeService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@Service
public class CashFreeServiceImpl implements CashFreeService {

    String appId = "105946f2eda7e0411d5379e8e3649501";
    String secretKey = "59fada3b1d55e9bf424359fce2e8b59ed0940d0d";
    String returnUrl = "https://www.imaya.co.in/ViewCartPage";
    //String paymentModes = "cc,dc,nb,paypal,upi,wallet";

    @Override
    public TransactionChecksumResponse generateTransactionChecksum(TransactionChecksumRequest transactionChecksumRequest) throws NoSuchAlgorithmException, InvalidKeyException {
        String data = "appId=" + appId + "&orderId=" +
                transactionChecksumRequest.getOrderId() +
                "&orderAmount=" +
                transactionChecksumRequest.getAmount() +
                "&returnUrl=" +
                returnUrl +
                "&paymentModes="; //+
                //paymentModes;
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec skspec = new SecretKeySpec(secretKey.getBytes(),"HmacSHA256");
        sha256_HMAC.init(skspec);
        String paymentToken = Base64.encodeBase64String(sha256_HMAC.doFinal(data.getBytes()));
        TransactionChecksumResponse transactionChecksumResponse = new TransactionChecksumResponse();
        transactionChecksumResponse.setToken(paymentToken);
        return transactionChecksumResponse;
    }
}
