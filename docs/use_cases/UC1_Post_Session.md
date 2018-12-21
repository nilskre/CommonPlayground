# Use-Case Specification: Posting a Session

# 1. Posting a Session

## 1.1 Brief Description
This use case allows users to post a session onto the session overview page. A session should include all important information that is necessary for other users. If a user wants to post a new session they need to fill in the template, which includes the fields:
- Name (short name for the session)
- Game (which game is the session for)
- Player maximum (Number of players which can still join this session including host)
- Place
- Time and Date
- Description

## 1.2 Mockup 
![Mockup Post a Session](../mockups/post_a_session_mockup.png)

# 2. Flow of Events

## 2.1 Basic Flow
- Users klicks on "Post new session"-button
- "Post-Session"-template pops up
- User fills in template
- User klicks on "finish"-button
- Session gets posted

### Activity Diagram
![Activity Diagram](../activity_diagrams/UCD1_Post_Session.png)

### .feature File

[.feature File Posting a session](../../frontend/app/src/androidTest/assets/UC1_Post_Session.feature)
![.feature File Posting a Session](../UC1_Feature_File.png)

## 2.2 Alternative Flows
n/a

# 3. Special Requirements
n/a

# 4. Preconditions
The preconditions for this use case are:
1. The user has started the App
2. The user has klicked on the "Post a session"-button

# 5. Postconditions
The postconditions for this use case are:
1. The session gets posted on the session overview page
2. The players participating index is incremented (session creator is the first person)

### 5.1 Save changes / Sync with server

If a session gets posted it needs to get synced with the server, so that every user can see the session.


# 6. Function Points
tbd
