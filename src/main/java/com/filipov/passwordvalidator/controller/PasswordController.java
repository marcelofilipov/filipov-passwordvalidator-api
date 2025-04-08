package com.filipov.passwordvalidator.controller;

import com.filipov.passwordvalidator.dto.PasswordRequest;
import com.filipov.passwordvalidator.dto.PasswordResponse;
import com.filipov.passwordvalidator.service.PasswordValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passwords/validate")
public class PasswordController {

    private final PasswordValidatorService validatorService;

    @Autowired
    public PasswordController(PasswordValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    @PostMapping
    public PasswordResponse checkIfPasswordIsValid(@RequestBody PasswordRequest request) {
        boolean isValid = validatorService.validate(request.passwordInput());
        return new PasswordResponse(isValid);
    }
}
