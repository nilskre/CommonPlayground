Feature: Use Case 8 Leaving a Session
  As a USER
  I want to have the opportunity to leave an already joined session.

  Background:
    Given The user is logged in
    And The user has joined one session

  @postsession-feature
  Scenario Outline: Add a new session
    When The user selects a joined session
    And The session details page is opened
    And The user clicks the "Leave" button
    Then The user is no participant of the session any more
    And The session details page is closed
