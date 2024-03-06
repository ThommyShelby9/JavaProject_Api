package com.example.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.AuthentificationDto;
import com.example.demo.models.AuthResponse;
import com.example.demo.models.UserApi;
import com.example.demo.service.UserServiceApi;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserServiceApi userService;
    private AuthenticationManager authenticationManager;

    @SuppressWarnings("deprecation")
    @PostMapping(value = "/inscription", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserApi> inscription(@RequestBody UserApi user) {
        UserApi result = this.userService.inscription(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/activate")
    public UserApi activation(@RequestBody Map<String, String> activation) {
        return this.userService.activation(activation);
    }

    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<AuthResponse> connection(@RequestBody UserApi user) {
        AuthResponse authResponse = this.userService.connection(user);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
    
    // private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // @PostMapping("/login")
    // public Map<String, String> login(AuthentificationDto authentificationDto){
    //     final Authentication authenticate = authenticationManager.authenticate(
    //         new UsernamePasswordAuthenticationToken(authentificationDto.username(), authentificationDto.password())
    //     );
    //     logger.info("resultat {}", authenticate.isAuthenticated());
    //     return null;
    // }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        // Vous pouvez implémenter la logique pour invalider le jeton côté serveur ici
        // Par exemple, vous pouvez ajouter le jeton à une liste noire ou supprimer le
        // jeton de la base de données si nécessaire

        // Pour invalider le jeton côté client, vous pouvez par exemple supprimer le
        // jeton des cookies
        Cookie cookie = new Cookie("jwt-token", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok("Vous avez été déconnecté avec succès.");
    }

}
