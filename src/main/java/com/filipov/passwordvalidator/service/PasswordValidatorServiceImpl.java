package com.filipov.passwordvalidator.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PasswordValidatorServiceImpl implements PasswordValidatorService {

    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-+";
    private static final int MIN_PASSWORD_LENGTH = 9;

    @Override
    public boolean validate(String password) {
        return isStructureValid(password) && hasRequiredCharacterTypes(password);
    }

    private boolean isStructureValid(String password) {
        return isLengthValid(password)
                && doesNotContainWhitespace(password)
                && hasNoRepeatedCharacters(password);
    }

    private boolean isLengthValid(String password) {
        return password != null && !password.isBlank() && password.length() >= MIN_PASSWORD_LENGTH;
    }

    private boolean doesNotContainWhitespace(String password) {
        return password.chars().noneMatch(Character::isWhitespace);
    }

    private boolean hasNoRepeatedCharacters(String password) {
        Set<Integer> uniqueChars = new HashSet<>();
        return password.codePoints().allMatch(uniqueChars::add);
    }

    private boolean hasRequiredCharacterTypes(String password) {
        var hasDigit = false;
        var hasLower = false;
        var hasUpper = false;
        var hasSpecial = false;

        for (int ch : password.codePoints().toArray()) {
            if (Character.isDigit(ch)) hasDigit = true;
            else if (Character.isLowerCase(ch)) hasLower = true;
            else if (Character.isUpperCase(ch)) hasUpper = true;
            else if (SPECIAL_CHARACTERS.indexOf(ch) != -1) hasSpecial = true;
        }

        return hasDigit && hasLower && hasUpper && hasSpecial;
    }

}
