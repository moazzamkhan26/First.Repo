package com.example.EmailDTO;

public record EmailRequest(
    String receiver,
    String subject,
    String message
) {}
