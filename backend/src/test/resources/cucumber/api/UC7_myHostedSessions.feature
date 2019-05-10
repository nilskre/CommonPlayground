Feature: UC7 Keeping Track (My hosted Sessions)
  Validates the correct behaviour of the backend api for my hosted sessions

  Background:
    Given The backend is active
    And I register a new account "User""123456789""a@b.c"
    And I login with "a@b.c""123456789" as test user type "sessionHost"
    And I post a new Session with correct Data as test user type "sessionHost"

  Scenario: get my hosted sessions
    When I request my hosted sessions
    Then  There should be my hosted sessions