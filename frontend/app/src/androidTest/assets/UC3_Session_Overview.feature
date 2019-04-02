Feature: Use Case 3 Session Overview
    As a USER
    I want to open the app and navigate to the Session Overview page
    On this page I want to see a full overview of all currently available sessions
    Also I want to see detailed information about the Sessions

  Background:
    Given The user is logged in

  @sessionoverview-feature
  Scenario Outline: The user wants to see all current active Sessions
    When Activity Session Overview is open
    Then The page should list all the current active Sessions
    And For each Session should the title <title> be shown
    And For each Session should the game <game> be shown
    And For each Session should the place <place> be shown
    And For each Session should the date <date> be shown

    Examples:
      | title   | game         | place         | date        |
      |  Raid   | Game         |  online       |  01.11.2018 |
      |  Cards  | Doppelkopf   |  Schlosspark  |  29.10.2019 |