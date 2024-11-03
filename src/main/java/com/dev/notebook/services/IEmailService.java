package com.dev.notebook.services;

public interface IEmailService {
    void sendNewAccountEmail(String name, String to, String token);
    void sendPasswordResetEmail(String name, String to, String token);
}
