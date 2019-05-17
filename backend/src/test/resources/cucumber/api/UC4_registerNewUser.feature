Feature: UC4 Create Account (Register new user)
  Validates the correct behaviour of the backend api for creating an account

  Scenario: successfully register new User
    When I register a new account "Hello""123456789""g@g.g"
    Then  The response should be "0"

 Scenario: register new User fails (username already exists)
   When I register a new account "Register1""123456""e@e.e"
   And I register a new account "Register1""123456789""f@f.f"
   Then  The response should be "-2"

 Scenario: register new User fails (email already exists)
   When I register a new account "UserRegister1""123456789""t@t.t"
   And I register a new account "UserRegister2""123456789""t@t.t"
   Then  The response should be "-3"