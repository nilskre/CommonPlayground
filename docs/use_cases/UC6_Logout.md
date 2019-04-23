# Use-Case Specification: Logout

# 1. Logout

## 1.1 Brief Description
This use case allows a logged in user to log out again.

## 1.2 Mockup
![Mockup](../mockups/Logout.png)

## 1.3 Screenshot
<img src="./Screenshots/UC6_Logout_Screenshot.png" alt="Screenshot: Menu with logout" width="300"/>

# 2. Flow of Events

## 2.1 Basic Flow
- User is logged in
- User opens menu and clicks "Logout"
- User token is removed
- App returns to login screen

### Activity Diagram
![Activity Diagram](../activity_diagrams/UCD6_Logout.png)

### .feature File
[.feature File Login](../../frontend/app/src/androidTest/assets/UC6_Logout.feature)
```Cucumber
Feature: Use Case 6 Logout
    As a USER
    I want to log out of my currently active session

  Scenario: Logout
    Given I am logged into my Common Playground account
    When I open the menu
    And I press logout
    Then the app should remove my credentials
    And return to the login screen

```

## 2.2 Alternative Flows
n/a

# 3. Special Requirements
- The user already has set up an account

# 4. Preconditions
The Preconditions for this use case are:
1. The user is logged in

# 5. Postconditions
Session credentials are removed

### 5.1 Save changes / Sync with server
The server is not involved in this use case, the app needs to return the same state as before the login

# 6. Function Points
![Function Points UC6_Logout](../function_points/UC6_Logout.png)
<img src="../function_points/Blue_print.png" alt="Function Points Blue_Print" width="500"/>

Total number of function points: 3.57