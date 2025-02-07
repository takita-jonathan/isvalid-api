package org.example.isvalidapi.unit.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.isvalidapi.controllers.PasswordValidatorController;
import org.example.isvalidapi.controllers.RestExceptionHandler;
import org.example.isvalidapi.domains.dtos.PasswordValidReqDTO;
import org.example.isvalidapi.domains.dtos.PasswordValidateReqDTO;
import org.example.isvalidapi.domains.exceptions.InvalidPasswordException;
import org.example.isvalidapi.services.PasswordValidatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class PasswordValidatorControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private PasswordValidatorController controller;

    @Mock
    private PasswordValidatorService service;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new RestExceptionHandler()).build();
    }

    @Test
    void shouldReturn200WhenPasswordIsValid() throws Exception {
        PasswordValidateReqDTO request = new PasswordValidateReqDTO("Abc123!@#");

        mockMvc.perform(post("/api/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Password is valid."))
                .andExpect(jsonPath("$.content.isValid").value(true));
    }

    @Test
    void shouldReturn400WhenPasswordIsInvalid() throws Exception {
        PasswordValidateReqDTO request = new PasswordValidateReqDTO("abc");

        doThrow(new InvalidPasswordException(List.of("The password must have at least 9 characters long.")))
                .when(service).validatePassword(any());

        mockMvc.perform(post("/api/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Password is invalid."))
                .andExpect(jsonPath("$.errors[0]").value("The password must have at least 9 characters long."));
    }

    @Test
    void shouldReturn200WhenPasswordWithAnnotationsIsValid() throws Exception {
        PasswordValidReqDTO request = new PasswordValidReqDTO("Abc123!@#");

        mockMvc.perform(post("/api/password/valid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Password is valid."))
                .andExpect(jsonPath("$.content.isValid").value(true));
    }

    @Test
    void shouldReturn400WhenPasswordWithAnnotationsIsInvalid() throws Exception {
        PasswordValidReqDTO request = new PasswordValidReqDTO("abc");

        mockMvc.perform(post("/api/password/valid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Password is invalid."))
                .andExpect(jsonPath("$.errors").value(containsInAnyOrder(
                        "The password must be at least 9 characters long.",
                        "The password must have at least one uppercase letter.",
                        "The password must have at least one number.",
                        "The password must have at least one special character."
                )));
    }

}