Feature: UC5 Login
  Validates the correct behaviour of the backend api for logging in

  Scenario: successfully logged in
    When I register a new account "User""123456789""a@b.c"
    And I login with "a@b.c""123456789" as test user type "sessionHost"
    Then  The response is my user ID

  Scenario: user does not exist
    When I login with "n@m.z""123456789" as test user type "anotherUser"
    Then  The login response should be "-4"

  Scenario: password incorrect
    When I register a new account "Horst""123456789""e@f.g"
    And I login with "e@f.g""12" as test user type "anotherUser"
    Then  The login response should be "-5"