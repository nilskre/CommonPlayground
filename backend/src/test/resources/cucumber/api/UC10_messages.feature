Feature: Part of UC10 Message Join
  Validates the correct behaviour of the backend api for the messaging system

  Background:
    And I register a new account "MessagesUser""123456789""m@m.m"
    And I login with "m@m.m""123456789" as test user type "anotherUser"

  Scenario: get my messages
    When "anotherUser" requests his messages
    Then  There should be my messages