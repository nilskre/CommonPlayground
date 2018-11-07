Feature: Functioning Activity New Session

  Background:
    Given The user is logged in
	And Activity New Session is open

  Scenario: Add a new session
    When The user types the title <title>
    And The user types the description <description>
    And The user types the game <game>
    And The user types the place <place>
    And The user types the date <date>
    And The user types the number of players <numberOfPlayers>
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