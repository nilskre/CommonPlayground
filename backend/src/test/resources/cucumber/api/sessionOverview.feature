Feature: Use Case 3 Session Overview
  Validates the correctness of the data on the api to the frontend

  Background:
    Given The backend is active

  Scenario Outline: return list with all current active sessions
    When I post a new Session with title <title> description <description> game <game> place <place> date <date> time <time> numberOfPlayers <numberOfPlayers> idOfHost <idOfHost> genre <genre> isOnline <isOnline>
    And  I look for the session list "http://localhost:8080/getSessionList"
    Then  There should be sessions
    And  There should be my PostedSession with title <title> description <description> game <game> place <place> date <date> time <time> numberOfPlayers <numberOfPlayers> idOfHost <idOfHost> genre <genre> isOnline <isOnline>

    Examples: Sessions
      | title | description | game       | place       | date       | time  | numberOfPlayers | idOfHost | genre | isOnline |
      | Raid  | online Game | Game       | online      | 01-11-2100 | 12:00 | 10              | 10       | 10    | online   |
      | Cards | fun         | Doppelkopf | Schlosspark | 29-10-2100 | 06:00 | 4               | 4        | 4     | offline  |