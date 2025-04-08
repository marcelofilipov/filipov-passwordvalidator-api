package com.filipov.passwordvalidator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordValidatorServiceTest {

    private PasswordValidatorService validator;

    @BeforeEach
    void setup() {
        validator = new PasswordValidatorServiceImpl();
    }

    @Test
    void deveInvalidarSenhaNula() {
        assertFalse(validator.validate(null));
    }

    @Test
    void deveInvalidarSenhaVazia() {
        assertFalse(validator.validate(""));
    }

    @Test
    void deveInvalidarSenhaEmBranco() {
        assertFalse(validator.validate(" "));
    }

    @ParameterizedTest
    @ValueSource(strings = {"aa", "bbb", "cccc"})
    void deveInvalidarSenhaComMenosDeNoveCaracteres(String password) {
        assertFalse(validator.validate(password));
    }

    @Test
    void deveValidarSenhaCorreta() {
        assertTrue(validator.validate("AbTp9!fok"));
    }

    @Test
    void deveInvalidarSenhaComCaracteresRepetidos() {
        assertFalse(validator.validate("AbTp9!foA"));
    }

    @Test
    void deveInvalidarSenhaSemCaracterEspecial() {
        assertFalse(validator.validate("AbTp9fokL"));
    }

    @Test
    void deveInvalidarSenhaComEspacoEmBranco() {
        assertFalse(validator.validate("AbTp9 %fok"));
    }

    @Test
    void deveInvalidarSenhaSemLetraMaiuscula() {
        assertFalse(validator.validate("abtp9!fok"));
    }

    @Test
    void deveInvalidarSenhaSemLetraMinuscula() {
        assertFalse(validator.validate("ABTP9!FOK"));
    }

    @Test
    void deveInvalidarSenhaSemDigito() {
        assertFalse(validator.validate("AbTp!fokX"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "AbTp9!fok", "AbTp9@fok", "AbTp9#fok", "AbTp9$fok",
            "AbTp9%fok", "AbTp9^fok", "AbTp9&fok", "AbTp9*fok",
            "AbTp9(fok", "AbTp9)fok", "AbTp9-fok", "AbTp9+fok"
    })
    void deveValidarSenhasComTodosCaracteresEspeciais(String password) {
        assertTrue(validator.validate(password));
    }

}