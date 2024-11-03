package com.dev.notebook.services.impl;

import com.dev.notebook.exceptions.ApiException;
import com.dev.notebook.services.IEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static com.dev.notebook.constants.Constant.NEW_USER_ACCOUNT_VERIFICATION;
import static com.dev.notebook.constants.Constant.PASWORD_RESET_REQUEST;
import static com.dev.notebook.utils.EmailUtils.getEmailMessage;
import static com.dev.notebook.utils.EmailUtils.getResetPasswordMessage;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService {


    private final JavaMailSender sender;

    @Value("${spring.mail.verify.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String fromMail;
    @Override
    @Async
    public void sendNewAccountEmail(String name, String email, String token) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            message.setFrom(fromMail);
            message.setTo(email);
            message.setText(getEmailMessage(name,host,token));
            sender.send(message);
        }catch (Exception ex){
            log.error(ex.getMessage());
            throw new ApiException("Unable to send email");
        }
    }

    @Override
    @Async
    public void sendPasswordResetEmail(String name, String email, String token) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(PASWORD_RESET_REQUEST);
            message.setFrom(fromMail);
            message.setTo(email);
            message.setText(getResetPasswordMessage(name,host,token));
            sender.send(message);
        }catch (Exception ex){
            log.error(ex.getMessage());
            throw new ApiException("Unable to send email");
        }
    }

}
