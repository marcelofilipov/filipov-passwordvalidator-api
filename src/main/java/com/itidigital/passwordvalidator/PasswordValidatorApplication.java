package com.itidigital.passwordvalidator;

import com.itidigital.passwordvalidator.request.PasswordRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/validate-password")
@SpringBootApplication
public class PasswordValidatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PasswordValidatorApplication.class, args);
    }

    @PostMapping
    public boolean validatePassword(@RequestBody PasswordRequest request) {
        if (request == null || request.getPasswordInput() == null || request.getPasswordInput().isBlank()) {
            return false;
        }
        return PasswordValidator.isValid(request.getPasswordInput());
    }
}

class PasswordValidator {
    public static boolean isValid(String password) {
        if (password == null || password.length() < 9) {
            return false;
        }

        boolean hasDigit = false;
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasSpecial = false;
        Set<Character> uniqueChars = new HashSet<>();
        String specialCharacters = "!@#$%^&*()-+";

        for (char ch : password.toCharArray()) {
            if (Character.isDigit(ch)) hasDigit = true;
            else if (Character.isLowerCase(ch)) hasLower = true;
            else if (Character.isUpperCase(ch)) hasUpper = true;
            else if (specialCharacters.contains(String.valueOf(ch))) hasSpecial = true;

            if (!uniqueChars.add(ch)) {
                return false; // Caractere repetido encontrado
            }
        }

        return hasDigit && hasLower && hasUpper && hasSpecial;
    }
}