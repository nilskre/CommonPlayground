Feature: UC2 Join Session
  Validates the correct behaviour of the backend api for joining a session

  Background:
    Given The backend is active
    # Host account
    And I register a new account "User""123456789""a@b.c"
    When I login with "a@b.c""123456789"
    And I post a new Session with correct Data

  Scenario: successful join request
    # User Account
    And I register a new account "Hello""123456789""d@e.f"
    When I login with "d@e.f""123456789"
    When I send a join request for one session
    # I = Session Host requests his messages
    When I request my messages
    And The Session Host approves the request "true"
    Then I have joined the session

  Scenario: host tries to  leave a session and fails
    When Host send a leave request for one session
    # TODO Then the retuern code should be -20

  Scenario: leave a session
    # User Account
    And I register a new account "Hello""123456789""d@e.f"
    When I login with "d@e.f""123456789"
    When I send a leave request for one session
    # I = Session Host requests his messages
    #When I request my messages
    #And The Session Host approves the request "false"
    #Then I have joined the session

  Scenario: rejected join request
    # User Account
    And I register a new account "Hello""123456789""d@e.f"
    When I login with "d@e.f""123456789"
    When I send a join request for one session
    # I = Session Host requests his messages
    When I request my messages
    And The Session Host approves the request "false"
    #Then I have joined the session