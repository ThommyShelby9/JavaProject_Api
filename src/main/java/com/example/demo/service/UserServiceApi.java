package com.example.demo.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.Date;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.JwtService;
import com.example.demo.models.AuthResponse;
import com.example.demo.models.UserApi;
import com.example.demo.models.Validation;
import com.example.demo.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceApi implements UserDetailsService{

    private UserRepository userRepository;
    private ValidationService validationService;
    private BCryptPasswordEncoder passwordEncoder;
    private JwtService jwtService;

    @Autowired
    private EntityManager entityManager;


    @Transactional // Assure que la transaction est gérée correctement
    public UserApi inscription(UserApi user) {
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            throw new RuntimeException("Votre mail n'est pas valide!");
        }
        if (user.getPassword() == null) {
            throw new IllegalArgumentException("Le mot de passe ne peut pas être null");
        }

        Optional<UserApi> existingUserOptional = userRepository.findByEmail(user.getEmail());

        existingUserOptional.ifPresent(existingUser -> {
            throw new RuntimeException("Un utilisateur avec cet e-mail existe déjà.");
        });

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        
        // Utilisation de merge pour fusionner l'entité dans le contexte persistant
        user = entityManager.merge(user); 

        validationService.save(user);
        return user;
    }
    public UserApi activation(Map<String, String> activation) {
        Validation validation = this.validationService.readCode(activation.get("code"));
    
        if (validation == null) {
            throw new RuntimeException("La validation n'a pas été trouvée pour le code fourni.");
        }
    
        Optional<UserApi> userOptional = this.userRepository.findById(validation.getUser().getId());
    
        if (userOptional.isPresent()) {
            UserApi user = userOptional.get();
            user.setEnabled(true);
            user.setEmailVerifyAt(Instant.now());
            validation.setActivation(Instant.now());
            UserApi updatedUser = this.userRepository.save(user); // Utilisez save() pour mettre à jour l'utilisateur
    
            return updatedUser;
        } else {
            throw new RuntimeException("L'utilisateur correspondant à la validation n'a pas été trouvé.");
        }
    }
    

    public AuthResponse connection(UserApi user) {
        Optional<UserApi> userExist = this.userRepository.findByEmail(user.getEmail());
    
        if (!userExist.isPresent()) {
            throw new RuntimeException("Utilisateur non trouvé!");
        }
    
        UserApi existingUser = userExist.get();
    
        // Utilisez passwordEncoder pour vérifier le mot de passe
        if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect!");
        }
        
        if (!existingUser.getEnabled()) { // Pas besoin de comparer avec true
            throw new RuntimeException("Votre compte n'est pas actif!");
        }
        String token = jwtService.generateToken(existingUser);
    
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUser(existingUser);
        authResponse.setToken(token);
    
        return authResponse;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
        return this.userRepository
        .findByFirstname(username);
    }
    
}

