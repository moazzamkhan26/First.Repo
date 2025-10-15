package com.example.EmailDTO;

public record EmailResponse(
    Long id,
    String receiver,
    String subject,
    String message
) {}
