Feature: UC7 Keeping Track (My hosted Sessions)
  Validates the correct behaviour of the backend api for my hosted sessions

  Background:
    And I register a new account "MyHostedSessions""123456789""w@w.w"
    And I login with "w@w.w""123456789" as test user type "sessionHost"
    And I post a new Session with correct Data as test user type "sessionHost"

  Scenario: get my hosted sessions
    When I request my hosted sessions
    Then  There should be my hosted sessions

  Scenario: get my joined sessions
    When I request my joined sessions
    Then  There are no joined sessions