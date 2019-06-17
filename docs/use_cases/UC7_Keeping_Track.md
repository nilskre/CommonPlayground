# Use-Case Specification: Keeping track of your sessions

# 1. Keeping track of your sessions

## 1.1 Brief Description
This use case allows a user to keep track of his sessions. The session has the following two categories: The sessions in which the user himself is the host, and the sessions to which he has joined.

## 1.2 Mockup
![Mockup keeping track hosted](../mockups/Keeping_Track_Hosted.png)
![Mockup keeping track joined](../mockups/Keeping_Track_Joined.png)

## 1.3 Screenshots
<img src="./Screenshots/UC7_Keeping_Track_Screenshot0.png" alt="Keeping track" width="300"/> <img src="./Screenshots/UC7_Keeping_Track_Screenshot1.png" alt="Keeping track" width="300"/> <img src="./Screenshots/UC7_Keeping_Track_Screenshot2.png" alt="Keeping track" width="300"/>

# 2. Flow of Events

## 2.1 Basic Flow
- User navigates to My Sessions with the navigation drawer
- User can see the sessions which he himself is the host
- User gets the possibility to switch the tab, so he gets to see the sessions he has joined.

### Activity Diagram
![Activity Diagram](../activity_diagrams/UCD7_Keeping_track.png)

### .feature File
n/a

## 2.2 Alternative Flows
n/a

# 3. Special Requirements
n/a

# 4. Preconditions
The Preconditions for this use case are:
1. The user has started the App
2. The user has navigated to My Sessions with the navigation drawer

# 5. Postconditions
n/a

### 5.1 Save changes / Sync with server
The displayed data should be updated whenever the user enters My Sessions again or when the user refreshes the page manually.

# 6. Function Points
![Function Points UC7_Keeping_Track](../function_points/UC7_Manage.png)
<img src="../function_points/Blue_print.png" alt="Function Points Blue_Print" width="500"/>

Total number of function points: 9.52