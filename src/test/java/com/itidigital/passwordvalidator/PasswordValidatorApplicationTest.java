package com.itidigital.passwordvalidator;

import com.itidigital.passwordvalidator.request.PasswordRequest;
import net.datafaker.Faker;
import net.datafaker.providers.base.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static net.datafaker.providers.base.Text.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class PasswordValidatorApplicationTest {

    @Mock
    private PasswordRequest request;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void testEmptyPasswordIsInvalid() {
        when(request.getPasswordInput()).thenReturn("");
        assertFalse(PasswordValidator.isValid(request.getPasswordInput()));
    }

    @Test
    void testBlankPasswordIsInvalid() {
        when(request.getPasswordInput()).thenReturn(" ");
        assertFalse(PasswordValidator.isValid(request.getPasswordInput()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"aa", "bb", "cc"})
    void testShortPasswordAaIsInvalid(String input) {
        when(request.getPasswordInput()).thenReturn(input);
        assertFalse(PasswordValidator.isValid(request.getPasswordInput()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ab", "bc", "cd"})
    void testShortPasswordAbIsInvalid() {
        assertFalse(PasswordValidator.isValid("ab"));
    }

    @Test
    void testMissingSpecialCharacterIsInvalid() {
        when(request.getPasswordInput()).thenReturn("AAAbbbCc");
        assertFalse(PasswordValidator.isValid(request.getPasswordInput()));
    }

    @Test
    void testRepeatedCharacterIsInvalid() {
        when(request.getPasswordInput()).thenReturn("AbTp9!foo");
        assertFalse(PasswordValidator.isValid(request.getPasswordInput()));
    }

    @Test
    void testRepeatedCharacterWithUppercaseIsInvalid() {
        when(request.getPasswordInput()).thenReturn("AbTp9!FoA");
        assertFalse(PasswordValidator.isValid(request.getPasswordInput()));
    }

    @Test
    void testPasswordWithSpaceIsInvalid() {
        when(request.getPasswordInput()).thenReturn("AbTp9 fok");
        assertFalse(PasswordValidator.isValid(request.getPasswordInput()));
    }

@Test
void testValidPassword() {
//        var faker = new Faker();
//
//        String passwordFake = faker.text().text(Text.TextSymbolsBuilder.builder()
//                        .len(9)
//                        .with(EN_UPPERCASE, 1)
//                        .with(EN_LOWERCASE,1)
//                        .with(DIGITS, 1)
//                        .with(DEFAULT_SPECIAL, 1 )
//                        .build());
//        System.out.println(passwordFake);

        when(request.getPasswordInput()).thenReturn("AbTp9!fok");
        assertTrue(PasswordValidator.isValid(request.getPasswordInput()));
    }

}