package com.example.eshopping.client.paytm.impl;

import com.example.eshopping.client.paytm.PaytmClient;
import com.example.eshopping.client.paytm.request.PaytmInitiateTransactionRequest;
import com.paytm.pg.merchant.PaytmChecksum;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class PaytmClientImpl implements PaytmClient {

    private static String INITIATE_TRANSACTION_API = "/theia/api/v1/initiateTransaction";

    @Value("${client.paytm.url}")
    private String baseUrl;

    @Value("${client.paytm.merchant.id}")
    private String merchantId;

    @Value("${client.paytm.merchant.key}")
    private String merchantKey;

    @Override
    public String initiateTransaction(PaytmInitiateTransactionRequest paytmInitiateTransactionRequest) {
        String post_data = createPostData(paytmInitiateTransactionRequest);

        URL url = null;
        try {
            url = new URL(baseUrl +
                    INITIATE_TRANSACTION_API +
                    "?mid=" + merchantId +
                    "&orderId=" +
                    paytmInitiateTransactionRequest.getOrderId());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        /* for Production */
// URL url = new URL("https://securegw.paytm.in/theia/api/v1/initiateTransaction?mid=YOUR_MID_HERE&orderId=ORDERID_98765");

        try {
            HttpURLConnection connection = createConnection(url);

            DataOutputStream requestWriter = new DataOutputStream(connection.getOutputStream());
            requestWriter.writeBytes(post_data);
            requestWriter.close();
            String responseData = "";
            InputStream is = connection.getInputStream();
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(is));
            if ((responseData = responseReader.readLine()) != null) {
                System.out.append("Response: " + responseData);
                return responseData;
            }
            responseReader.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private HttpURLConnection createConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        return connection;
    }

    private String createPostData(PaytmInitiateTransactionRequest paytmInitiateTransactionRequest) {
        JSONObject paytmParams = new JSONObject();

        JSONObject body = createBody(paytmInitiateTransactionRequest);

        JSONObject head = new JSONObject();
        head.put("signature", createChecksum(body));

        paytmParams.put("body", body);
        paytmParams.put("head", head);

        return paytmParams.toString();
    }

    private JSONObject createBody(PaytmInitiateTransactionRequest paytmInitiateTransactionRequest) {
        JSONObject body = new JSONObject();
        body.put("requestType", "Payment");
        body.put("mid", merchantId);
        body.put("websiteName", "DEFAULT");
        body.put("orderId", paytmInitiateTransactionRequest.getOrderId());
        body.put("callbackUrl", "https://www.imaya.co.in/ViewCartPage");

        body.put("txnAmount", createTxnAmount(paytmInitiateTransactionRequest));
        body.put("userInfo", createUserInfo(paytmInitiateTransactionRequest));
        return body;
    }


    private JSONObject createTxnAmount(PaytmInitiateTransactionRequest paytmInitiateTransactionRequest) {
        JSONObject txnAmount = new JSONObject();
        txnAmount.put("value", paytmInitiateTransactionRequest.getAmount());
        txnAmount.put("currency", "INR");
        return txnAmount;
    }

    private JSONObject createUserInfo(PaytmInitiateTransactionRequest paytmInitiateTransactionRequest) {
        JSONObject userInfo = new JSONObject();
        userInfo.put("custId", "CUST_001");
        return userInfo;
    }

    private String createChecksum(JSONObject body) {
        String checksum = null;
        try {
            checksum = PaytmChecksum.generateSignature(body.toString(), merchantKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checksum;
    }

}
