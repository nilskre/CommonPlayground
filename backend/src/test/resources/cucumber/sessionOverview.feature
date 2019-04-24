Feature: Use Case 3 Session Overview
  Validates the correctness of the data on the api to the frontend

  Scenario: return list with all current active sessions
    Given The backend is active
    And I send a request to the backend
    When  I look for "http://localhost:8080/getSessionList"
    Then  There should be sessions
