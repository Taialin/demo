package com.example.demo.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Lazy
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();
    }
}
