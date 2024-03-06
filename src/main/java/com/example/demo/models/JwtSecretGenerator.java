package com.example.demo.models;

import java.security.SecureRandom;
import java.util.Base64;

public class JwtSecretGenerator {
     public static void main(String[] args) {
        // Définir la longueur de la clé (en octets)
        int keyLength = 256;

        // Créer un générateur de nombre aléatoire sécurisé
        SecureRandom secureRandom = new SecureRandom();

        // Générer une clé secrète
        byte[] keyBytes = new byte[keyLength / 8];
        secureRandom.nextBytes(keyBytes);

        // Convertir la clé en format Base64
        String jwtSecret = Base64.getEncoder().encodeToString(keyBytes);

        // Afficher la clé secrète générée
        System.out.println("JWT Secret: " + jwtSecret);
    }
}
