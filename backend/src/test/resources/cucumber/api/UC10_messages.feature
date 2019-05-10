Feature: Part of UC2 Join
  Validates the correct behaviour of the backend api for the messaging system

  Background:
    Given The backend is active
    And I register a new account "User""123456789""a@b.c"
    And I login with "a@b.c""123456789" as test user type "sessionHost"

  Scenario: get my messages
    When "sessionHost" requests his messages
    Then  There should be my messages