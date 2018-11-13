Feature: Use Case 1 Post a Session
  As a USER
  I want to open the app and be able to post a new Session at the Session Overview Page
  Therefore I want the fields: title, description, game, place, date and numberOfPlayers able to be filled in

  Background:
    Given The user is logged in
    And Activity New Session is open

  Scenario: Add a new session
    When The user types the title <title> and the input is correct
    And The user types the description <description> and the input is correct
    And The user types the game <game> and the input is correct
    And The user types the place <place> and the input is correct
    And The user types the date <date> and the input is correct
    And The user types the number of players <numberOfPlayers> and the input is correct
    And The user presses the publish button
    Then A Request is sent
    And The posting screen is closed

  Examples:
  | title   | description  | game         | place         | date        | numberOfPlayers   |
  |  Raid   |  online Game |  Game        |  web          |  01.11.2018 |  10               |
  |  Cards  |  fun         |  Doppelkopf  |  Schlosspark  |  29.10.2019 |  4                |


  Scenario: Leaving the Activity New Session without sending a Request
    When The user presses the Back button
    Then No Request is sent
    And The posting screen is closed