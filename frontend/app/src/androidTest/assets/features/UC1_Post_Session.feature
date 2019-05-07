Feature: Use Case 1 Posting a Session
  As a USER
  I want to open the app and be able to post a new Session at the Session Overview Page
  Therefore I want the fields: title, description, game, place, date and numberOfPlayers able to be filled in

  Background:
    Given The user is logged in
    And Activity New Session is open

  @postsession-feature
  Scenario Outline: Add a new session
    When The user types the title <title> and the input is correct
    And The user types the game <game> and the input is correct
    And The user chooses <type> as type
    And The user chooses <genre> as genre
    And The user types the post code <postCode> and the input is correct
    And The user picks the date <date>
    And The user picks the time <time>
    And The user types the number of players <numberOfPlayers> and the input is correct
    And The user types the description <description> and the input is correct
    And The user presses the publish button
    Then A Request is sent
    And The posting screen is closed

    Examples: Sessions
      | title | game       | type     | genre     | postCode  | date       | time  | numberOfPlayers | description |
      | Raid  | Game       | Online   | MMO RPG   | -         | 01-11-2020 | 12:00 | 10              | online Game |
      | Cards | Doppelkopf | Offline  | Card Game | 23456     | 29-10-2019 | 06:00 | 4               | fun         |

  @postsession-feature
  Scenario: Leaving the Activity New Session without sending a Request
    When The user presses the Back button
    Then No Request is sent
    And The posting screen is closed
