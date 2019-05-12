Feature: UC8 Leave Session
  Validates the correct behaviour of the backend api for joining a session

  #Background:
  #  And I register a new account "LeaveUser""123456789""a@a.a"
  #  And I login with "a@a.a""123456789" as test user type "sessionHost"
  #  And I post a new Session with correct Data as test user type "sessionHost"
#
  #Scenario: successful join request
  #  When I register a new account "NormalUser""123456789""g@h.j"
  #  And I login with "g@h.j""123456789" as test user type "normalUser"
  #  And I send a join request for one session
  #  And I request my pending sessions as test user type "normalUser"
  #  And There is the session I want to join
  #  And "sessionHost" requests his messages
  #  And The Session Host approves the request "true"
  #  Then I have joined the session
#
  #Scenario: sessionHost tries to  leave a session and fails
  #  When "sessionHost" sends a leave request for one session
  #  Then The return code should be -20
#
  #Scenario: normalUser leaves a session
  #  And I login with "g@h.j""123456789" as test user type "normalUser"
  #  And "normalUser" sends a leave request for one session
  #  When "normalUser" requests his messages
  #  Then There should be a leave message
  #  When "sessionHost" requests his messages
  #  Then There should be a user has left message