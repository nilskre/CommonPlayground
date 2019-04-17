# Use-Case Specification: Login

# 1. Login

## 1.1 Brief Description
This use case allows a user with an account to log in which is the basis for posting and joining sessions as well as tracking one's sessions.

## 1.2 Mockup
![Mockup login](../mockups/Login.png)
![Mockup login fail1](../mockups/Login_Fail1.png)
![Mockup login fail2](../mockups/Login_Fail2.png)

## 1.3 Screenshots
<img src="./Screenshots/UC5_Login_Screenshot.png" alt="Screenshot login" width="300"/> <img src="./Screenshots/UC5_Login_Screenshot2.png" alt="Screenshot login fail 1" width="300"/> <img src="./Screenshots/UC5_Login_Screenshot3.png" alt="Screenshot login fail 2" width="300"/>

There are several more error messages.

# 2. Flow of Events

## 2.1 Basic Flow
- User starts app
- User automatically gets promted to sign in
- User provides correct credentials
- Login is confirmed

### Activity Diagram
![Activity Diagram](../activity_diagrams/UCD5_Login.png)

### .feature File
[.feature File Login](../../frontend/app/src/androidTest/assets/features/UC5_Login.feature)
```Cucumber
Feature: Use Case 5 Login
    As a USER
    I want to open the app and log into my exisitng account to be able to use the app.
    I want to provide username and password and to be forwarded to the main page while the app recognizes me.

  Scenario: I attempt to log in with an unknown user
    Given I open the app and I am prompted to provide my user credentials
    When I type in an unknown username <account>
    And I press the login button
    Then The app should show a warning saying "username unknown"
    And the active view remains <login>

  Scenario: I attempt to log in with a known user and a wrong password
    Given I open the app and I am prompted to provide my user credentials
    When I type in an incorrect password <password>
    And I press the login button
    Then The app should show a warning saying "user password combination incorrect"
    And the active view remains <login>

  Scenario: I attempt to log in with correct credentials
    Given I open the app and I am prompted to provide my user credentials
    When I type in a known username <account>
    And I type in the password <password> that is stored for that username
    Then The app should forward me to the <session overview> view
And The app should remember the user as logged in in this session
```

## 2.2 Alternative Flows
- User provides wrong credentials and is informed that login failed

# 3. Special Requirements
- The user already has set up an account

# 4. Preconditions
The Preconditions for this use case are:
1. The user has started the App

# 5. Postconditions
n/a

### 5.1 Save changes / Sync with server
Server needs to validate credentials and provide user information so that other use cases can be traced to the respective user

# 6. Function Points
![Function Points UC5_Login](../function_points/UC5_Login.png)
<img src="../function_points/Blue_print.png" alt="Function Points Blue_Print" width="500"/>