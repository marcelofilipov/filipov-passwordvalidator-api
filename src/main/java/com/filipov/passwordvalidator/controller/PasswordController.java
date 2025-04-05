package com.filipov.passwordvalidator.controller;

import com.filipov.passwordvalidator.dto.PasswordRequest;
import com.filipov.passwordvalidator.dto.PasswordResponse;
import com.filipov.passwordvalidator.service.PasswordValidatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validate-password")
public class PasswordController {

    private final PasswordValidatorServiceImpl validatorService;

    @Autowired
    public PasswordController(PasswordValidatorServiceImpl validatorService) {
        this.validatorService = validatorService;
    }

    @PostMapping
    public PasswordResponse validatePassword(@RequestBody PasswordRequest request) {
        boolean isValid = validatorService.validate(request.getPasswordInput());
        return new PasswordResponse(isValid);
    }
}
