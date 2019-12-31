package com.orangeboy.service;

public interface EmailService {
    void sendEmail(String to, String subject, String context);
}
