# Use-Case Specification: Create Account

# 1. Create Account

## 1.1 Brief Description
This use case allows anyone to register and create an account. Also the data will be secure saved in our data base.

## 1.2 Mockup
![Registration](../mockups/Registration.png)
![Registration Fail 1](../mockups/Registration_Fail1.png)
![Registration Fail 2](../mockups/Registration_Fail2.png)
![Registration Fail 3](../mockups/Registration_Fail3.png)

## 1.3 Screenshots
<img src="./Screenshots/UC4_Create_Account_Screenshot.png" alt="Screenshot register fail 1" width="300"/> <img src="./Screenshots/UC4_Create_Account_Screenshot2.png" alt="Screenshot register fail 2" width="300"/> <img src="./Screenshots/UC4_Create_Account_Screenshot3.png" alt="Screenshot register fail 3" width="300"/> <img src="./Screenshots/UC4_Create_Account_Screenshot4.png" alt="Screenshot register fail 4" width="300"/>

There are several more error messages.

# 2. Flow of Events

## 2.1 Basic Flow
- user klicks on register
- register screen opens
- user fills in his data
- user clicks on submit
- data will be validated and send to the databse

### Activity Diagram
![Activity Diagram](../activity_diagrams/UCD4_Create_Account.png)

### .feature File
[.feature File Login](../../frontend/app/src/androidTest/assets/UC4_Create_Account.feature)
```Cucumber
Feature: Use Case 4 Create Account
  As a USER
  I want to create an account to sign in to the app

  Scenario Outline: create new account
    Given I have the app installed and open the register screen
    When I fill in the <username> field
    And I fill in the <password> field
    And I fill in the <repeatPassword> field with the same value
    And I click on the send Button
    Then the app should validate the input
    And send the data to the backend
    And return to the login screen

    Examples:
      | username   | password  | repeatPassword |
      |  iBims     |  1234Game |  1234Game      |
      |  hello     |  fun      |  fun           |  


```

## 2.2 Alternative Flows
n/a

# 3. Special Requirements
User needs an email account to register

# 4. Preconditions
The Preconditions for this use case are:
1. The user has the app installed
2. The user clicks on register

# 5. Postconditions
The app opens the login screen

### 5.1 Save changes / Sync with server
Data gets encoded and saved in database

# 6. Function Points
![Function Points UC4_Create_Account](../function_points/UC4_Account.png)
<img src="../function_points/Blue_print.png" alt="Function Points Blue_Print" width="500"/>