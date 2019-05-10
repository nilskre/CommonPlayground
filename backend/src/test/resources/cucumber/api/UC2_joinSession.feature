Feature: UC2 Join Session
  Validates the correct behaviour of the backend api for joining a session

  Background:
    Given The backend is active
    And I register a new account "User""123456789""a@b.c"
    And I login with "a@b.c""123456789" as test user type "sessionHost"
    And I post a new Session with correct Data as test user type "sessionHost"

  Scenario: successful join request
    When I register a new account "Hello""123456789""d@e.f"
    And I login with "d@e.f""123456789" as test user type "normalUser"
    And I send a join request for one session
    And I request my pending sessions as test user type "normalUser"
    And There is the session I want to join
    And "sessionHost" requests his messages
    And The Session Host approves the request "true"
    Then I have joined the session

  Scenario: normalUser leaves the session
    When I register a new account "Hello""123456789""d@e.f"
    And I login with "d@e.f""123456789" as test user type "normalUser"
    And "normalUser" sends a leave request for one session
    When "normalUser" requests his messages
    Then There should be a leave message

  Scenario: rejected join request
    When I register a new account "Hello""123456789""d@e.f"
    And I login with "d@e.f""123456789" as test user type "normalUser"
    And I send a join request for one session
    When "sessionHost" requests his messages
    And The Session Host approves the request "false"
    And "normalUser" requests his messages
    Then There should be a reject message