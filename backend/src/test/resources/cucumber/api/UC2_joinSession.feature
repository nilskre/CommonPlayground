Feature: UC2 Join Session
  Validates the correct behaviour of the backend api for joining a session

  Background:
    And I register a new account "UserSessionHost""123456789""x@y.z"
    And I login with "x@y.z""123456789" as test user type "sessionHost"
    And I post a new Session with correct Data as test user type "sessionHost"

  Scenario: successful join request
    When I register a new account "NormalUser""123456789""g@h.j"
    And I login with "g@h.j""123456789" as test user type "normalUser"
    And I send a join request for one session
    And I request my pending sessions as test user type "normalUser"
    And There is the session I want to join
    And "sessionHost" requests his messages
    And The Session Host approves the request "true"
    Then I have joined the session

  #Scenario: normalUser leaves the session
  #  And I login with "g@h.j""123456789" as test user type "normalUser"
  #  And "normalUser" sends a leave request for one session
  #  When "normalUser" requests his messages
  #  Then There should be a leave message
#
  #Scenario: rejected join request
  #  And I login with "g@h.j""123456789" as test user type "normalUser"
  #  And I send a join request for one session
  #  When "sessionHost" requests his messages
  #  And The Session Host approves the request "false"
  #  And "normalUser" requests his messages
  #  Then There should be a reject message