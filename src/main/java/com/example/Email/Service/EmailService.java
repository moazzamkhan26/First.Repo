package com.example.Email.Service;

import com.example.EmailDTO.EmailRequest;
import com.example.EmailDTO.EmailResponse;

public interface EmailService {
    EmailResponse sendEmail(EmailRequest request);
}
