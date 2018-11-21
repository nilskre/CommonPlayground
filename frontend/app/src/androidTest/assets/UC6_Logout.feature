Feature: Use Case 6 Logout
    As a USER
    I want to log out of my currently active session

  Scenario: Logout
    Given I am logged into my Common Playground account
    When I open the menu
    And I press logout
    Then the app should remove my credentials
    And return to the login screen
