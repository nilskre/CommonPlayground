Feature: UC1 Posting a Session
  Validates the correct behaviour of the backend api for posting a session

  Scenario: post a new Session
    When I register a new account "PostingUser""123456789""p@p.p"
    And I login with "p@p.p""123456789" as test user type "sessionHost"
    When I post a new Session with correct Data as test user type "sessionHost"
    And  I look for the session list "http://localhost:8080/getSessionList"
    Then  There should be my PostedSession with correct Data
