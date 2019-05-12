Feature: UC5 Login
  Validates the correct behaviour of the backend api for logging in

  Background:
    #Given The backend is active
    And I register a new account "User""123456789""a@b.c"

  Scenario: successfully logged in
    When I login with "a@b.c""123456789" as test user type "sessionHost"
    Then  The response is my user ID

  Scenario: user does not exist
    When I login with "n@m.z""123456789" as test user type "anotherUser"
    Then  The response should be "-4"

  Scenario: password incorrect
    When I login with "a@b.c""12" as test user type "anotherUser"
    Then  The response should be "-5"