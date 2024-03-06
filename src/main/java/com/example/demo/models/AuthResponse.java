package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private UserApi user;
    private String token;
}
