Feature: Use Case 3 Session Overview
    As a USER
    I want to open the app and navigate to the Session Overview page
    On this page I want to see a full overview of all currently available sessions
    Also I want to see detailed information about the Sessions

  Scenario: I want to see all current active Sessions
    Given I open the App
    When I open the Session Overview page
    Then The page should list all the current active Sessions
    And For each Session should the title <title> be shown
    And For each Session should the game <game> be shown
    And For each Session should the place <place> be shown
    And For each Session should the date <date> be shown
    And For each Session should the players count <numberOfPlayers> be shown

  Examples:
  | title   | game         | place         | date        | numberOfPlayers   |
  |  Raid   | Wow          |  online       |  01.11.2018 |  10               |
  |  Mario  | Mario Party  |  online       |  07.07.2018 |  30               |
  | Fortnite| Battle       |  online       |  25.09.2018 |  40               |
