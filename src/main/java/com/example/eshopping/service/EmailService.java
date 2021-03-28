package com.example.eshopping.service;

import com.example.eshopping.model.email.EmailSendRequest;
import com.example.eshopping.model.email.EmailSendResponse;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailService {

    EmailSendResponse sendEmail(EmailSendRequest emailSendRequest) throws UnsupportedEncodingException, MessagingException;

}
