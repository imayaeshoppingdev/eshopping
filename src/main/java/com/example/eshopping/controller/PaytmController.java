package com.example.eshopping.controller;

import com.example.eshopping.model.paytm.InitiateTransactionRequest;
import com.example.eshopping.model.paytm.InitiateTransactionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paytm.pg.merchant.PaytmChecksum;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequestMapping("/paytm")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class PaytmController {

    @PostMapping("/transaction/initiate")
    public ResponseEntity<InitiateTransactionResponse> initiateTransaction(@RequestBody InitiateTransactionRequest initiateTransactionRequest) {

        InitiateTransactionResponse initiateTransactionResponse= new InitiateTransactionResponse();
        JSONObject paytmParams = new JSONObject();

        JSONObject body = new JSONObject();
        body.put("requestType", "Payment");
        body.put("mid", "iShZhj34740735870234");
        body.put("websiteName", "WEBSTAGING");
        body.put("orderId", initiateTransactionRequest.getOrderId());
        body.put("callbackUrl", "https://www.imaya.co.in/ViewCartPage");

        JSONObject txnAmount = new JSONObject();
        txnAmount.put("value", initiateTransactionRequest.getAmount());
        txnAmount.put("currency", "INR");

        JSONObject userInfo = new JSONObject();
        userInfo.put("custId", "CUST_001");
        body.put("txnAmount", txnAmount);
        body.put("userInfo", userInfo);

        String checksum = null;
        try {
            checksum = PaytmChecksum.generateSignature(body.toString(), "MBim6a7N_1IoGaP7");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject head = new JSONObject();
        head.put("signature", checksum);

        paytmParams.put("body", body);
        paytmParams.put("head", head);

        String post_data = paytmParams.toString();

        URL url = null;
        try {
            url = new URL("https://securegw-stage.paytm.in/theia/api/v1/initiateTransaction?mid=iShZhj34740735870234&orderId=" + initiateTransactionRequest.getOrderId());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        /* for Production */
// URL url = new URL("https://securegw.paytm.in/theia/api/v1/initiateTransaction?mid=YOUR_MID_HERE&orderId=ORDERID_98765");

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            DataOutputStream requestWriter = new DataOutputStream(connection.getOutputStream());
            requestWriter.writeBytes(post_data);
            requestWriter.close();
            String responseData = "";
            InputStream is = connection.getInputStream();
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(is));
            if ((responseData = responseReader.readLine()) != null) {
                System.out.append("Response: " + responseData);
                initiateTransactionResponse = new ObjectMapper().readValue(responseData, InitiateTransactionResponse.class);
            }
            responseReader.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseEntity.ok(initiateTransactionResponse);
    }
}


