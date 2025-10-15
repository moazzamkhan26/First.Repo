package com.example.EmailController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Email.Service.EmailService;
import com.example.EmailDTO.EmailRequest;
import com.example.EmailDTO.EmailResponse;

@RestController
@RequestMapping("/api/email")
public class Controller {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public EmailResponse sendEmail(@RequestBody EmailRequest request) {
        // Simply call service; service handles mapping + DB + sending
        return emailService.sendEmail(request);
    }
}
