package org.example.isvalidapi.integration.configurations;

import io.cucumber.spring.CucumberContextConfiguration;
import jakarta.annotation.PostConstruct;
import org.example.isvalidapi.IsvalidApiApplication;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = IsvalidApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberSpringConfiguration {

    @PostConstruct
    public void init() {
        System.out.println("✅ CucumberSpringConfiguration foi carregada corretamente!");
    }

}
