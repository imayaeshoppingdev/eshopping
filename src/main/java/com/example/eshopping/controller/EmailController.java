package com.example.eshopping.controller;

import com.example.eshopping.model.email.EmailSendRequest;
import com.example.eshopping.model.email.EmailSendResponse;
import com.example.eshopping.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class EmailController {

    EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<EmailSendResponse> initiateTransaction(@RequestBody EmailSendRequest emailSendRequest) {
        //TODO Validate request
        try {
            EmailSendResponse emailSendResponse = emailService.sendEmail(emailSendRequest);
            return ResponseEntity.ok(emailSendResponse);
        }
        catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

}
