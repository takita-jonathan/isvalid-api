@all @password @spring
Feature: Password Validation With Spring
  As a user
  I want to validate my password
  So that I know if it meets security requirements

  Background:
    Given the validation mode is "spring"

  Scenario: Valid password
    Given the user provides a password "Abc123!@#"
    Then the response status should be 200
    And the response message should be "Password is valid."
    And the password should be valid

  Scenario: Invalid password
    Given the user provides a password "abc"
    Then the response status should be 400
    And the response message should be "Password is invalid."
    And the response should contain the following errors:
      | The password must be at least 9 characters long. |
      | The password must have at least one uppercase letter. |
      | The password must have at least one number. |
      | The password must have at least one special character. |

  Scenario: Password too short
    Given the user provides a password "A1a!"
    Then the response status should be 400
    And the response message should be "Password is invalid."
    And the response should contain the following errors:
      | The password must be at least 9 characters long. |

  Scenario: Password missing uppercase letter
    Given the user provides a password "abc123!@#"
    Then the response status should be 400
    And the response message should be "Password is invalid."
    And the response should contain the following errors:
      | The password must have at least one uppercase letter. |

  Scenario: Password missing lowercase letter
    Given the user provides a password "ABC123!@#"
    Then the response status should be 400
    And the response message should be "Password is invalid."
    And the response should contain the following errors:
      | The password must have at least one lowercase letter. |

  Scenario: Password missing digit
    Given the user provides a password "Abcdef!@#"
    Then the response status should be 400
    And the response message should be "Password is invalid."
    And the response should contain the following errors:
      | The password must have at least one number. |

  Scenario: Password missing special character
    Given the user provides a password "Abc123456"
    Then the response status should be 400
    And the response message should be "Password is invalid."
    And the response should contain the following errors:
      | The password must have at least one special character. |

  Scenario: Password has repeated characters
    Given the user provides a password "Abc123!@A"
    Then the response status should be 400
    And the response message should be "Password is invalid."
    And the response should contain the following errors:
      | The password cannot contain repeated characters. |