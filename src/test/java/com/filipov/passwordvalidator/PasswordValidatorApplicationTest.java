package com.filipov.passwordvalidator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class PasswordValidatorApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void mainMethodRunsWithoutException() {
        PasswordValidatorApplication.main(new String[]{});
    }

    @Test
    void shouldLoadPasswordControllerBean() {
        assertThat(applicationContext.containsBean("passwordController")).isTrue();
    }

}
