Feature: Use Case 1 Posting a Session
  As a USER
  I want to open the app and be able to post a new Session at the Session Overview Page
  Therefore I want the fields: title, description, game, place, date and numberOfPlayers able to be filled in

  Background:
    Given The user is logged in
    And Activity New Session is open

  @postsession-feature
  Scenario Outline: Add a new session
    When
    When The user types the title <title> and the input is correct
    And The user types the description <description> and the input is correct
    And The user types the game <game> and the input is correct
    And The user types the place <place> and the input is correct
    And The user types the date <date> and the input is correct
    And The user types the time <time> and the input is correct
    And The user types the number of players <numberOfPlayers> and the input is correct
    And The user presses the publish button
    Then A Request is sent
    And The posting screen is closed

    Examples: Sessions
      | title | description | game       | place       | date       | time  | numberOfPlayers |
      | Raid  | online Game | Game       | web         | 01.11.2018 | 12:00 | 10              |
      | Cards | fun         | Doppelkopf | Schlosspark | 29.10.2019 | 06:00 | 4               |

  @postsession-feature
  Scenario: Leaving the Activity New Session without sending a Request
    When The user presses the Back button
    Then No Request is sent
    And The posting screen is closed
