Feature: UC1 Post a Session
  Validates the correct behaviour of the backend api for posting a session

  Scenario: post a new Session
    Given The backend is active
    And I post a new Session
    When  I look for "http://localhost:8080/getSessionList"
    Then  There should be my PostedSession
