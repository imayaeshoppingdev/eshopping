package com.example.eshopping.client.aws;

import com.example.eshopping.client.aws.request.EmailRequest;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface SesClient {

    String sendEmail(EmailRequest emailRequest) throws UnsupportedEncodingException, MessagingException;
}
