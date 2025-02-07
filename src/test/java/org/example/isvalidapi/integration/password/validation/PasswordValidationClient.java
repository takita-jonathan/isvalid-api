package org.example.isvalidapi.integration.password.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.isvalidapi.domains.dtos.PasswordValidateReqDTO;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;

@Getter
@Slf4j
@Component
@RequiredArgsConstructor
public class PasswordValidationClient {

    private ObjectMapper objectMapper;

    private Response response;

    public void validatePassword(String password) {
        PasswordValidateReqDTO requestDTO = new PasswordValidateReqDTO(password);

        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBasePath("/api/password/validate")
                .setContentType(ContentType.JSON)
                .setBody(requestDTO)
                .build();

        log.info("Sending password validation request...");

        this.response = given()
                .log().all()
                .spec(requestSpec)
                .post();

        log.info("Received response:");
        response.then().log().all();
    }

    public void validatePasswordSpring(String password) {
        PasswordValidateReqDTO requestDTO = new PasswordValidateReqDTO(password);

        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBasePath("/api/password/valid")
                .setContentType(ContentType.JSON)
                .setBody(requestDTO)
                .build();

        log.info("Sending password validation request...");

        this.response = given()
                .log().all()
                .spec(requestSpec)
                .post();

        log.info("Received response:");
        response.then().log().all();
    }
}
