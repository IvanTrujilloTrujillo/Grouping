package com.ironhack.edgeservice.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtil {
    public static String encodePassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }


    public static void main(String[] args) {
        System.out.println(encodePassword("9876"));
        System.out.println(encodePassword("1234"));
    }
}
