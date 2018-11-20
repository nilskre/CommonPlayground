Feature: Use Case 3 Session Overview
  Validates the correctness of the data on the api to the frontend

  Scenario: return list with all current active sessions
    Given I send a request to the backend
    When  I look for "/getSessionList"
    Then  There should be sessions
