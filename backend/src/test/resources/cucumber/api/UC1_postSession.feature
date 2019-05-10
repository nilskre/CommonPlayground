Feature: UC1 Posting a Session
  Validates the correct behaviour of the backend api for posting a session

  Background:
    Given The backend is active

  Scenario: post a new Session
    When I post a new Session with correct Data as test user type "normalUser"
    And  I look for the session list "http://localhost:8080/getSessionList"
    Then  There should be my PostedSession with correct Data
