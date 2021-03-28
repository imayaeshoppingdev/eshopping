package com.example.eshopping.serviceImpl;

import com.example.eshopping.client.aws.SesClient;
import com.example.eshopping.client.aws.request.EmailRequest;
import com.example.eshopping.model.email.EmailSendRequest;
import com.example.eshopping.model.email.EmailSendResponse;
import com.example.eshopping.service.EmailService;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;


@Service
public class EmailServiceImpl implements EmailService {

    private SesClient sesClient;

    public EmailServiceImpl(SesClient sesClient) {
        this.sesClient = sesClient;
    }

    @Override
    public EmailSendResponse sendEmail(EmailSendRequest emailSendRequest) throws UnsupportedEncodingException, MessagingException {
        EmailRequest emailRequest = new EmailRequest.Builder()
                .withFrom(emailSendRequest.getFrom())
                .withFromName(emailSendRequest.getFromName())
                .withTo(emailSendRequest.getTo())
                .withSubject(emailSendRequest.getSubject())
                .withBody(emailSendRequest.getBody())
                .build();
        sesClient.sendEmail(emailRequest);
        return new EmailSendResponse();
    }
}
