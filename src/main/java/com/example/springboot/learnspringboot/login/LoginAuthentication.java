package com.example.springboot.learnspringboot.login;

import org.springframework.stereotype.Service;

@Service
public class LoginAuthentication {
    public boolean authenticate(String username, String password) {
        boolean isValidUsername = username.equalsIgnoreCase("qwer");
        boolean isValidPassword = password.equalsIgnoreCase("1234");

        return isValidUsername && isValidPassword;
    }
}
