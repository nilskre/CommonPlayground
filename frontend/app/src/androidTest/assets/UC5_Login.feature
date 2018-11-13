Feature: Use Case 5 Login
    As a USER
    I want to open the app and log into my exisitng account to be able to use the app.
    I want to provide username and password and the be forwarded to the main page while the app recognizes me.

  Scenario: I attempt to log in with an unknown user
    Given I open the app and I am prompted to provide my user credentials
    When I type in an unkown username <account>
    And I press the login button
    Then The app should show a warning saying "username unkown"
    And the active view remains <login>

  Scenario: I attempt to log in with a known user and a wrong password
    Given I open the app and I am prompted to provide my user credentials
    When I type in an incorrect password <password>
    And I press the login button
    Then The app should show a warning saying "password incorrect"
    And the active view remains <login>

  Scenario: I attempt to log in with correct credentials
  Given I open the app and I am prompted to provide my user credentials
  When I type in a known username <account>
  And I type in the password <password> that is stored for that username
  Then The app should forward me to the <session overview> view
  And The app should remember the user as logged in in this session
