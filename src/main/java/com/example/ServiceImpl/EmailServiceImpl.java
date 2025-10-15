package com.example.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.Email.Service.EmailService;
import com.example.EmailDTO.EmailRequest;
import com.example.EmailDTO.EmailResponse;
import com.example.EmailEntity.EmailEntity;
import com.example.EmailMapper.EmailMapper;
import com.example.EmailRepo.EmailRepository;
import com.example.ExceptionHandling.EmailSendingException;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private EmailMapper emailMapper;

    @Override
    public EmailResponse sendEmail(EmailRequest request) {

        if (request.receiver() == null || request.receiver().isEmpty()) {
            throw new IllegalArgumentException("Receiver email cannot be null or empty");
        }

        try {
            // Map DTO → Entity
            EmailEntity record = emailMapper.toEntity(request);
            EmailEntity saved = emailRepository.save(record);

            // Build email message using StringBuffer
            StringBuffer sb = new StringBuffer();
            sb.append("Hello ").append(saved.getReceiver()).append(",\n\n");
            sb.append("Your email has been sent successfully.\n");
            sb.append("Subject: ").append(saved.getSubject()).append("\n\n");
            sb.append("Message: ").append(saved.getMessage());

            // Send email
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("your_email@gmail.com");
            message.setTo(saved.getReceiver());
            message.setSubject(saved.getSubject());
            message.setText(sb.toString());

            mailSender.send(message);

            return emailMapper.toResponse(saved);

        } catch (MailException e) {
            throw new EmailSendingException("Failed to send email: " + e.getMessage());
        } catch (Exception e) {
            throw new EmailSendingException("An unexpected error occurred");
        }
    }
}