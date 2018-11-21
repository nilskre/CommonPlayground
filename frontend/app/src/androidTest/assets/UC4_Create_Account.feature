Feature: Use Case 4 Create Account
  As a USER
  I want to create an account to sign in to the app

  Scenario Outline: create new account
    Given I have the app installed and open the register screen
    When I fill in the <username> field
    And I fill in the <password> field
    And I fill in the <repeatPassword> field with the same value
    And I click on the send Button
    Then the app should validate the input
    And send the data to the backend
    And return to the login screen

    Examples:
      | username   | password  | repeatPassword |
      |  iBims     |  1234Game |  1234Game      |
      |  hello     |  fun      |  fun           |  
