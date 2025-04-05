package com.filipov.passwordvalidator.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PasswordValidatorServiceImpl implements PasswordValidatorService {

    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-+";

    @Override
    public boolean validate(String password) {
        if (isInvalidLength(password) || containsWhitespace(password) || hasDuplicateChars(password)) {
            return false;
        }

        return hasRequiredCharacters(password);
    }

    private boolean isInvalidLength(String password) {
        return password == null || password.isBlank() || password.length() < 9;
    }

    private boolean hasDuplicateChars(String password) {
        Set<Character> uniqueChars = new HashSet<>();
        for (char ch : password.toCharArray()) {
            if (!uniqueChars.add(ch)) {
                return true; // Encontrou caractere repetido
            }
        }
        return false;
    }

    private boolean hasRequiredCharacters(String password) {
        var hasDigit = false;
        var hasLower = false;
        var hasUpper = false;
        var hasSpecial = false;

        for (char ch : password.toCharArray()) {
            if (Character.isDigit(ch)) hasDigit = true;
            else if (Character.isLowerCase(ch)) hasLower = true;
            else if (Character.isUpperCase(ch)) hasUpper = true;
            else if (SPECIAL_CHARACTERS.indexOf(ch) != -1) hasSpecial = true;
        }

        return hasDigit && hasLower && hasUpper && hasSpecial;
    }

    private boolean containsWhitespace(String password) {
        return password.contains(" ");
    }

}
