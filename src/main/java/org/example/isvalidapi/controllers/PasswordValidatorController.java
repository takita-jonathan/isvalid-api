package org.example.isvalidapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.isvalidapi.domains.dtos.PasswordValidReqDTO;
import org.example.isvalidapi.domains.dtos.PasswordValidateReqDTO;
import org.example.isvalidapi.domains.dtos.PasswordValidateResDTO;
import org.example.isvalidapi.domains.dtos.base.ResponseDTO;
import org.example.isvalidapi.services.PasswordValidatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/password")
@RequiredArgsConstructor
@Tag(name = "Password Validation", description = "Endpoints to validate passwords")
public class PasswordValidatorController {

    private final PasswordValidatorService service;

    @PostMapping("/validate")
    @Operation(summary = "Validate a password", description = "Returns whether the password meets the security criteria")
    public ResponseEntity<ResponseDTO<PasswordValidateResDTO>> validatePassword(@RequestBody PasswordValidateReqDTO req) {
        service.validatePassword(req.getPassword());
        return ResponseEntity.ok(
                ResponseDTO.<PasswordValidateResDTO>builder()
                        .message("Password is valid.")
                        .content(PasswordValidateResDTO.builder().isValid(true).build())
                        .build()
        );
    }

    @PostMapping("/valid")
    @Operation(summary = "Validate a password", description = "Returns whether the password meets the security criteria")
    public ResponseEntity<ResponseDTO<PasswordValidateResDTO>> validatePasswordWithAnnotations(@Valid @RequestBody PasswordValidReqDTO req) {
        return ResponseEntity.ok(
                ResponseDTO.<PasswordValidateResDTO>builder()
                        .message("Password is valid.")
                        .content(PasswordValidateResDTO.builder().isValid(true).build())
                        .build()
        );
    }

}
