package com.example.demo.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.demo.models.UserApi;
import com.example.demo.models.Validation;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.ValidationRepository;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class ValidationService {
    
    private ValidationRepository validationRepository;
    private MailSenderService mailSender;
    private UserRepository userRepository; // Ajoutez le référentiel UserRepository
    
    public UserApi save(UserApi user ){
        Validation validation = new Validation();
        @SuppressWarnings("null")
        UserApi savedUser = userRepository.save(user); // Sauvegarde ou récupère l'utilisateur depuis la base de données
        validation.setUser(savedUser); // Utilise l'utilisateur persistant
        Instant creation = Instant.now();
        validation.setCreation(creation);
        Instant expiration = creation.plus(10, ChronoUnit.MINUTES);
        validation.setExpire(expiration);
        Random random = new Random();
        int randomInteger = random.nextInt(9999999);
        String code = String.format("%06d", randomInteger);
        validation.setCode(code);
        this.validationRepository.save(validation);
        this.mailSender.sendMail(validation);
        return savedUser;
    }

    public Validation readCode(String code) {
        return validationRepository.findByCode(code);
    }
    
}
