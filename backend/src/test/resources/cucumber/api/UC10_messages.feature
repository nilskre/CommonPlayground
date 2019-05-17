Feature: Part of UC10 Message Join
  Validates the correct behaviour of the backend api for the messaging system

  Background:
    And I register a new account "MessagesUser""123456789""m@m.m"
    And I login with "m@m.m""123456789" as test user type "sessionHost"
    And I post a new Session with correct Data as test user type "sessionHost"

  Scenario: get my messages
    When I register a new account "NormalUserMessages""123456789""z@z.z"
    And I login with "z@z.z""123456789" as test user type "normalUser"
    And I send a join request for one session
    And "sessionHost" requests his messages
    Then  There should be my messages