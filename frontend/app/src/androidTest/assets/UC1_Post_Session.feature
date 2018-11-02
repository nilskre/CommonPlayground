Feature: The user is able to post a new session

  Scenario: I add a new session to CommonPlayground
    Given I open the App
    When I click on the Plus Button on the right side at the bottom
    Then A screen with the possibility for posting a session appears
    And The user types the title <title>
    And The user types the game <game>
    And The user types the place <place>
    And The user types the date <date>
    And The user taps the number of players <numberOfPlayers>
    When The user taps the publish button
    Then A Request is sent
    And The posting screen is closed

  Examples:
  | title   | game         | place         | date       | numberOfPlayers  |
  |  Raid   |  Game        |  web          | 01.11.2018 | 10               |
  |  Cards  |  Doppelkopf  |  Schlosspark  | 29.10.2019 | 4                |