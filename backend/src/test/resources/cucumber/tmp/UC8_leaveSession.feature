Feature: UC8 Leave Session
  Validates the correct behaviour of the backend api for joining a session

  Background:
    #Given The backend is active
    And I register a new account "User""123456789""a@b.c"
    And I login with "a@b.c""123456789" as test user type "sessionHost"
    And I post a new Session with correct Data as test user type "sessionHost"

  Scenario: successful join request
    When I register a new account "Hello""123456789""d@e.f"
    And I login with "d@e.f""123456789" as test user type "normalUser"
    And I send a join request for one session
    And "sessionHost" requests his messages
    And The Session Host approves the request "true"
    When "normalUser" requests his messages
    Then I have joined the session
    #And stopSpringBoot

  Scenario: sessionHost tries to  leave a session and fails
    When "sessionHost" sends a leave request for one session
    Then The return code should be -20
    #And stopSpringBoot

 # Scenario: normalUser leaves a session
 #   When I register a new account "Hello""123456789""d@e.f"
 #   And I login with "d@e.f""123456789" as test user type "normalUser"
 #   And "normalUser" sends a leave request for one session
 #   When "normalUser" requests his messages
 #   Then There should be a leave message
 #   When "sessionHost" requests his messages
 #   Then There should be a user has left message