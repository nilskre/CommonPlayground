Feature: UC1 Posting a Session
  Validates the correct behaviour of the backend api for posting a session

  Background:
    Given The backend is active

  Scenario Outline: post a new Session
    When I post a new Session with title <title> description <description> game <game> place <place> date <date> time <time> numberOfPlayers <numberOfPlayers> idOfHost <idOfHost> genre <genre> isOnline <isOnline>
    And  I look for the session list "http://localhost:8080/getSessionList"
    Then  There should be my PostedSession with title <title> description <description> game <game> place <place> date <date> time <time> numberOfPlayers <numberOfPlayers> idOfHost <idOfHost> genre <genre> isOnline <isOnline>

    Examples: Sessions
      | title | description | game       | place       | date       | time  | numberOfPlayers | idOfHost | genre | isOnline |
      | Raid  | online Game | Game       | online      | 01-11-2100 | 12:00 | 10              | 10       | 10    | online   |
      | Cards | fun         | Doppelkopf | Schlosspark | 29-10-2100 | 06:00 | 4               | 4        | 4     | offline  |