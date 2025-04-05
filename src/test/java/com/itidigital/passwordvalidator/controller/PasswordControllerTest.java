package com.itidigital.passwordvalidator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itidigital.passwordvalidator.dto.PasswordRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PasswordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String toJson(String passwordInput) throws Exception {
        PasswordRequest request = new PasswordRequest();
        request.setPasswordInput(passwordInput);
        return objectMapper.writeValueAsString(request);
    }

    @Test
    void deveRetornarTrueParaSenhaValida() throws Exception {
        mockMvc.perform(post("/validate-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson("AbTp9!fok")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(true));
    }

    @Test
    void deveRetornarFalseParaSenhaComCaracteresRepetidos() throws Exception {
        mockMvc.perform(post("/validate-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson("AbTp9!foA")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(false));
    }

    @Test
    void deveRetornarFalseParaSenhaVazia() throws Exception {
        mockMvc.perform(post("/validate-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson("")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(false));
    }

    @Test
    void deveRetornarFalseParaSenhaComEspaco() throws Exception {
        mockMvc.perform(post("/validate-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(" ")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(false));
    }

}