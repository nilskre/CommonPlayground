Feature: UC 3 Session Overview
  Validates the correctness of the data on the api to the frontend

  Background:
    #Given The backend is active

  Scenario: return list with all current active sessions
    When I post a new Session with correct Data as test user type "sessionHost"
    And  I look for the session list "http://localhost:8080/getSessionList"
    Then There should be sessions
    And  There should be my PostedSession with correct Data