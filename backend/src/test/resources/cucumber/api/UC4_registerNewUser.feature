Feature: UC4 Create Account (Register new user)
  Validates the correct behaviour of the backend api for creating an account

  #Background:
    #Given The backend is active

  Scenario: successfully register new User
    When I register a new account "Hello""123456789""a@b.c"
    Then  The response should be "0"

 Scenario: register new User fails (username already exists)
   When I register a new account "User""123456""d@e.f"
   And I register a new account "User""123456789""g@h.i"
   Then  The response should be "-2"

 Scenario: register new User fails (email already exists)
   When I register a new account "User1""123456789""a@b.c"
   And I register a new account "User2""123456789""a@b.c"
   Then  The response should be "-3"