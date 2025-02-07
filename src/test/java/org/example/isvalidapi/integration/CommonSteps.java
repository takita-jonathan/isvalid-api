package org.example.isvalidapi.integration;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import org.springframework.boot.test.web.server.LocalServerPort;

public class CommonSteps {

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        System.out.println("🚀 Testes rodando na porta aleatória: " + port);
    }

}
