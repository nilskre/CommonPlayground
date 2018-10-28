# Common Playground - Software Requirements Specification 

## Table of contents
- [Table of contents](#table-of-contents)
- [Introduction](#1-introduction)
    - [Purpose](#11-purpose)
    - [Scope](#12-scope)
    - [Definitions, Acronyms and Abbreviations](#13-definitions-acronyms-and-abbreviations)
    - [References](#14-references)
    - [Overview](#15-overview)
- [Overall Description](#2-overall-description)
    - [Vision](#21-vision)
    - [Use Case Diagram](#22-use-case-diagram)
- [Specific Requirements](#3-specific-requirements)
    - [Functionality](#31-functionality)
    - [Usability](#32-usability)
    - [Reliability](#33-reliability)
    - [Performance](#34-performance)
    - [Supportability](#35-supportability)
    - [Design Constraints](#36-design-constraints)
    - [Online User Documentation and Help System Requirements](#37-on-line-user-documentation-and-help-system-requirements)
    - [Purchased Components](#purchased-components)
    - [Interfaces](#39-interfaces)
    - [Licensing Requirements](#310-licensing-requirements)
    - [Legal, Copyright And Other Notices](#311-legal-copyright-and-other-notices)
    - [Applicable Standards](#312-applicable-standards)
- [Supporting Information](#4-supporting-information)

## 1. Introduction

### 1.1 Purpose
This Software Requirements Specification (SRS) describes all specifications for the Application "Common Playground". It includes an overview about this project and its vision, detailed information about the planned features and boundary conditions of the development process.


### 1.2 Scope
The project is going to be realized as an Android App.  
  
Actors of this App can be users or moderators.  
  
Planned Subsystems are: 
* Notice Board:  
The notice board is the essential part of the user interface. Game sessions should be visualized as postings including relevant information about the session. Those should be partly standardized by a form with a free text option for specifics. The data must be stored accordingly.
* Account System:  
Users can create Accounts so sessions can be connected to a person as well as join requests. User data must be stored alongside the post data.
* Friend List and User Rating:  
Once the account system is created there will be the option to mark users as favorites. Also users or game sessions should get a rating to counter abuse.
* Connecting People:  
The host of a game session has to be notified when someone wants to join their game. Both must be able to get in touch to organize the details so messages between the host and the guest have to be connected. This could be done via automated emails or a custom in-app system. For this an account system is needed.
* Storing Data:  
User data for accounts and possibly profiles has to be stored. Also the game sessions have to be stored as datasets containing the form contents and possibly contact data. The data storage will form the foundation for the visualization, account system and the search feature.
* Finding your Game:  
We need a tag system so everyone looking to join a game can search for the kind of games they are interested in. Possibly other aspects can be searchable, such as place or date. Tags must be stored and a search function developed. 

### 1.3 Definitions, Acronyms and Abbreviations
| Abbrevation | Explanation                            |
| ----------- | -------------------------------------- |
| SRS         | Software Requirements Specification    |
| UC          | Use Case                               |
| n/a         | not applicable                         |
| tbd         | to be determined                       |
| UCD         | overall Use Case Diagram               |
| FAQ         | Frequently asked Questions             |

### 1.4 References

| Title                                                              | Date       | Publishing organization   |
| -------------------------------------------------------------------|:----------:| ------------------------- |
| [Common Playground Blog](http://commonplayground.wordpress.com)    | 18.10.2018 | Common Playground Team    |
| [GitHub](https://github.com/nilskre/CommonPlayground)              | 18.10.2018 | Common Playground Team    |


### 1.5 Overview
The following chapter provides an overview of this project with vision and overall Use Case Diagram. The third chapter (Requirements Specification) deliveres more details about the specific requirements in terms of functionality, usability and design parameters. Finally there is a chapter with supporting information. 
    
## 2. Overall Description

### 2.1 Vision
Inspired by carpool coordination services like ‘BlaBlaCar’ or ‘Mitfahrzentrale’ we want to build an application to coordinate game sessions. We plan to create a platform for people who are looking for other people to play games with. Covering online multiplayer games, tabletop, pen and paper or regular board games we want to provide a kind of bulletin board where people can state what they want to play, when and where they want to do it and how many people they are looking for. Others can then react to the postings and virtually join the play session to be connected by us so everyone can coordinate the actual play session together on a Common Playground.

### 2.2 Use Case Diagram

![OUCD](./UseCaseDiagramCP.png)

## 3. Specific Requirements

### 3.1 Functionality
This section will explain the different use cases, you could see in the Use Case Diagram, and their functionality.

#### 3.1.1 Posting a session
This feature is the essential one of our project. The user gets the possibility to post a session. Therefore, he has to select a game and also set the time when he wants to play. Later on, when we also implemented offline games, he has to set a location, too.
![Post a Session](./Use_cases/UCS_Post_Session.md)

#### 3.1.2 Finding and joining a session
There is also the possibility to join an existing game session. Therefore, the user can find a session by tags and then by pressing a button join the session by another player. Later on, finding a session will be provided by geolocalization that the users can search for a session in a specific area.
![Join a Session](./Use_cases/UCS_Join_Session.md)

#### 3.1.3 Getting in touch
There must be the possibility that two people who want to play together can communicate with each other. At first, we want to implement that when a user post a session, he has to enter his mail address. The player who joins the session gets than the possibility to contact the first user. Later on, when we will have implemented profiles, then there will be another way to communicate with each other.

#### 3.1.4 Keeping track of your sessions and leave a session
The app provides the user with a seperate page view where he gets an overview of all sessions he posted or he joined in. Here, the user can see who joined his posted session. The user gets also the possibility to delete a session he posted or to leave a session he joined in.

#### 3.1.5 Logging in
Later on, the app will provide the possibility to register and log in. This will also make the usability easier when a user wants to manage his sessions, post or join a session because he doesn't have to enter his mail address every time.

#### 3.1.6 Presenting yourself and checking out others
With the possibility to log in there comes another functionality, the profile. Every user will have his one profile where he can write some informations about hisself. Because of the privacy policy in Europe, the user has the possibility to only write those information he wants other people to see. Owing to the profile, users can also check out other players and learn e.g. the favorite games about the user.

#### 3.1.7 Reporting users and managing friends
After a session, the app provides the users with the possibility to report the other participants. This is helpful because we want a community with fair players. Additionally, when they found an interesting person they can also add them to there friend list which also has a seperate page view.

#### 3.1.8 Ban users and deleting posts
There are also some functionalities for the admin. He will get the possibility to ban users and to delete any posts.

### 3.2 Usability
We plan on designing the user interface as intuitive and self-explanatory as possible to make the user feel as comfortable as possible using the app. Though help documents will be available, it should not be necessary to use them.

#### 3.2.1 No training time needed
Our goal is that a user installs the android application, opens it, get’s a small introduction screen and is able to use all features without any explanation or help.

#### 3.2.2 Familiar Feeling
We want to implement an app with familiar designs and functions. This way the user is able to interact in familiar ways with the app without having to get to know new interfaces.

### 3.3 Reliability

#### 3.3.1 Availability
The server shall be available 95% of the time. This also means we have to figure out the "rush hours" of our app because the downtime of the server is only tolerable when as less as possible players want to use the app.

#### 3.3.2 Defect Rate
Our goal is that we have no lost of any data. This is important that the game sessions can carry on, even after a downtime of the server.

### 3.4 Perfomance

#### 3.4.1 Capacity
The system should be able to manga thousands of requests. Also it should be possible to register as many users as necessary.

#### 3.4.2 Storage 
Smartphones don't provide much storage. Therefore we are aiming to keep the needed Storage as small as possible.

#### 3.4.3 App perfomance / Response time
To provide the best App perfomance we aim to keep the response time as low as possible. This will make the user experience much better.

### 3.5 Supportability

#### 3.5.1 Coding Standards
We are going to write the Code by using all of the most common clean code standards. For example we will name our variables and methods by their functionalities. This will keep the Code easy to read by everyone and make further developement much easier.

#### 3.5.2 Testing Strategy
The Application will have a high test coverage and all important functionalities and edge cases should be tested. Further mistakes in the Implementation will be discovered instant and it will be easy to locate the error. 

### 3.6 Design Constraints
We are trying to provide a modern and easy to handle design for the UI aswell as for the Architecture of our Application. To achieve that the functionalities will be kept as modular as possible.

Because we are progamming an android app we chose Java as our programming Language. Also we are using the common MVC-Architecture to keep the frontend and backend seperated. To make the communication between these two parts easy, we will implement a RESTful-API between them which will provide the data in JSON-Format. 
The supported Platforms will be:
- Android 4.4 and higher
- Java 8 and higher

### 3.7 On-line User Documentation and Help System Requirements
The usage of the app should be as intuitive as possible so it won't need any further documentation. If the user needs some help we will implement a "Help"-Button in the App which includes a FAQ and a formular to contact the developement team.

### 3.8 Purchased Components
We don't have any purchased Components yet. If there will be purchased Components in the future we will list them here.

### 3.9 Interfaces

#### 3.9.1 User Interfaces
The User interfaces that will be implented are:
- Dashboard - lists all session and makes it possible to filter session
- Session Page - shows detailed information about the session and makes it possible to connect session attendants for example via chat
- Login - this page is used to log in or to register
- Overwiew of the sessions - shows all the sessions a user participate in
- Friend List - friends can be added and joined
- Profile - makes it possible to post information about yourself
- User Information - shows additional information about users (for example: Language, Country, Favourite Games, etc.)
- Settings - shows the Settings

#### 3.9.2 Hardware Interfaces
(n/a)

#### 3.9.3 Software Interfaces
The app will be runnable on Android 4.4 and higher. iOS won't be featured at the moment.

#### 3.9.4 Communication Interfaces
The Server and Hardware will communicate using the http protocol. 

### 3.10 Licensing Requirements

### 3.11 Legal, Copyright, and Other Notices
The Logo is licensed to the Common Playground Team and is only allowed to use for the application. We do not take responsibilty for any incorrect data or errors in the application.

### 3.12 Applicable Standards
The development will follow the common clean code standards and naming conventions. Also we will create a Definition of Done which will be added here as soon as its complete.

## 4. Supporting Information
For any further information you can contact the Common Playground Team or check our [Common Playground Blog](http://commonplayground.wordpress.com). 
The Team Members are:
- Celina Adam
- Inga Batton
- Nils Krehl 
- Denis Reibel

<!-- Picture-Link definitions: -->
[OUCD]: https://github.com/IB-KA/CommonPlayground/blob/master/UseCaseDiagramCP.png "Overall Use Case Diagram"
