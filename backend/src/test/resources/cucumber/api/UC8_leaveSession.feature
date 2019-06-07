Feature: UC8 Leave Session
  Validates the correct behaviour of the backend api for joining a session

  Scenario: successful join request
    And I register a new account "LeaveUser""123456789""a@a.a"
    And I login with "a@a.a""123456789" as test user type "sessionHost"
    And I post a new Session with correct Data as test user type "sessionHost"
    When I register a new account "t""123456789""n@n.n"
    And I login with "n@n.n""123456789" as test user type "normalUser"
    And I send a join request for one session
    And I request my pending sessions as test user type "normalUser"
    And There is the session I want to join
    And "sessionHost" requests his messages
    And The Session Host approves the request "true"

  Scenario: normalUser leaves a session
    And I register a new account "LeaveUser2""123456789""r@r.r"
    And I login with "r@r.r""123456789" as test user type "sessionHost"
    And I post a new Session with correct Data as test user type "sessionHost"
    And I register a new account "normalUserLeave""123456789""l@l.l"
    And I login with "l@l.l""123456789" as test user type "normalUserLeave"
    And "normalUserLeave" sends a leave request for one session
    When "normalUserLeave" requests his messages
    Then There should be a leave message

  Scenario: sessionHost tries to  leave a session and fails
    When I unset global session id var
    And I register a new account "LeaveUser3""123456789""2@2.2"
    And I login with "2@2.2""123456789" as test user type "sessionHost"
    And I post a new Session with correct Data as test user type "sessionHost"
    And "sessionHost" sends a leave request for one session
    Then The return code should be -20

  Scenario: user wants to join pending and then leaving
    When I unset global session id var
    And I register a new account "LeaveUser22""123456789""rr@rr.rr"
    And I login with "rr@rr.rr""123456789" as test user type "sessionHost"
    And I post a new Session with correct Data as test user type "sessionHost"
    And I register a new account "normalUserLeave22""123456789""ll@ll.ll"
    And I login with "ll@ll.ll""123456789" as test user type "normalUser"
    And I send a join request for one session
    And "normalUser" sends a leave request for one session
    When "normalUser" requests his messages
    Then There should be a leave message

  Scenario: corrupt frontend exception
    Then  Corrupt request sent and internal server error is returned leave