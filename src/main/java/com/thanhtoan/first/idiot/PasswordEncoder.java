package com.thanhtoan.first.idiot;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPass = "thanhtoan";
        String encoded = encoder.encode(rawPass);
        System.out.println(encoded);
    }
}
