package com.clasify.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Hasher {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "adminpass";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }
}