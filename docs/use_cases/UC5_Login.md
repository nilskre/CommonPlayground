# Use-Case Specification: Getting an overview

# 1. Login

## 1.1 Brief Description
This uses cases allows a user with an account to log which is the basis for posting and joining sessions as well as tracking one's sessions.

## 1.2 Mockup
![Mockup login](../mockups/TBD)

# 2. Flow of Events

## 2.1 Basic Flow
- User starts app
- User automatically gets promted to sign in
- User provides correct credentials and is forwarded to welcome stream

### Activity Diagram
![Activity Diagram](../activity_diagrams/UCD5_Login.png)

### .feature File


## 2.2 Alternative Flows
- User provides wrong credentials and is informed that login failed

# 3. Special Requirements
n/a

# 4. Preconditions
The Preconditions for this use case are:
1. The user has started the App
2. The user already has set up an account

# 5. Postconditions
n/a

### 5.1 Save changes / Sync with server
Server needs to validate credentials and provide user information so that other use cases can be traced to the respective user

# 6. Function Points
n/a
