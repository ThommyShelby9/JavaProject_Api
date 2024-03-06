package com.example.demo.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.models.Validation;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MailSenderService {
    JavaMailSender mailSender;

    public void sendMail(Validation validation) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("rmissimawu@gmail.com");
        message.setTo(validation.getUser().getEmail());
        message.setSubject("Votre code d'activation");

        String texte = String.format(" Bonjour %s Votre code d'activation est le suivant : %s . " +
                                     "", validation.getUser().getFirstname(), validation.getCode());
        message.setText(texte);
        mailSender.send(message);
    }
}

