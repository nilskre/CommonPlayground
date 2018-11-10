Feature: Use Case 1 Post a Session
    As a USER
    I want to open the app and be able to post a new Session at the Session Overview Page
    Therefore I want the fields: title, description, game, place, date and numberOfPlayers able to be filled in

  Scenario: I add a new session to CommonPlayground
    Given I open the App
    When I click on the Plus Button on the right side at the bottom
    Then A screen with the possibility for posting a session appears
    And The user types the title <title> and the input is correct
    And The user types the description <description> and the input is correct
    And The user types the game <game> and the input is correct
    And The user types the place <place> and the input is correct
    And The user types the date <date> and the input is correct
    And The user taps the number of players <numberOfPlayers> and the input is correct
    When The user taps the publish button
    Then A Request is sent
    And The posting screen is closed

  Examples:
  | title   | description  | game         | place         | date        | numberOfPlayers   |
  |  Raid   |  online Game |  Game        |  web          |  01.11.2018 |  10               |
  |  Cards  |  fun         |  Doppelkopf  |  Schlosspark  |  29.10.2019 |  4                |