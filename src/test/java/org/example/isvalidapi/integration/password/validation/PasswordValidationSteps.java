package org.example.isvalidapi.integration.password.validation;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class PasswordValidationSteps {

    private final PasswordValidationClient passwordValidationClient;
    private String validationMode = "service";

    @Given("the validation mode is {string}")
    public void setValidationMode(String mode) {
        this.validationMode = mode;
    }

    @Given("the user provides a password {string}")
    public void theUserProvidesAPassword(String password) {
        if (validationMode.equals("service")) {
            passwordValidationClient.validatePassword(password);
        } else {
            passwordValidationClient.validatePasswordSpring(password);
        }
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int expectedStatus) {
        assertThat(passwordValidationClient.getResponse().getStatusCode()).isEqualTo(expectedStatus);
    }

    @And("the response message should be {string}")
    public void theResponseMessageShouldBe(String expectedMessage) {
        String actualMessage = passwordValidationClient.getResponse().jsonPath().getString("message");
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @And("the password should be valid")
    public void thePasswordShouldBeValid() {
        boolean isValid = passwordValidationClient.getResponse().jsonPath().getBoolean("content.isValid");
        assertThat(isValid).isTrue();
    }

    @And("the response should contain the following errors:")
    public void theResponseShouldContainTheFollowingErrors(List<String> expectedErrors) {
        List<String> actualErrors = passwordValidationClient.getResponse().jsonPath().getList("errors");
        assertThat(actualErrors).containsExactlyInAnyOrderElementsOf(expectedErrors);
    }


}
