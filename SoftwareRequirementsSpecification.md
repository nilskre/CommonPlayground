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

    - Einführung 


### 1.2 Scope

 - Type of application: App
 - Actors
 - Subsystems

### 1.3 Definitions, Acronyms and Abbreviations

    - Abkürzungen
    - Synonyme

### 1.4 References

| Title                                                              | Date       | Publishing organization   |
| -------------------------------------------------------------------|:----------:| ------------------------- |
| [Common Playground Blog](http://commonplayground.wordpress.com)    | 18.10.2018 | Common Playground Team    |
| [GitHub](https://github.com/nilskre/CommonPlayground)              | 18.10.2018 | Common Playground Team    |


### 1.5 Overview

    - Dokumenten aufbau
    - Leserhinweise
    
## 2. Overall Description

### 2.1 Vision
Inspired by carpool coordination services like ‘BlaBlaCar’ or ‘Mitfahrzentrale’ we want to build an application to coordinate game sessions. We plan to create a platform for people who are looking for other people to play games with. Covering online multiplayer games, tabletop, pen and paper or regular board games we want to provide a kind of bulletin board where people can state what they want to play, when and where they want to do it and how many people they are looking for. Others can then react to the postings and virtually join the play session to be connected by us so everyone can coordinate the actual play session together on a Common Playground.

### 2.2 Use Case Diagram



## 3. Specific Requirements

### 3.1 Functionality

    - UML Diagramm erklärung
    - Use Cases/Übersichtsseiten Erklärung

#### 3.1.1 

    - 

#### 3.1.2

#### 3.1.3

#### 3.1.4 

#### 3.1.5

#### 3.1.6 

#### 3.1.7

#### 3.1.8 

#### 3.1.9 

### 3.2 Usability

- User-feeling beim Bedienen


#### 3.2.1


#### 3.2.2 

### 3.3 Reliability

    - Ausfallsicherheit
    - Datenspeicherung

#### 3.3.1 


#### 3.3.2 

### 3.4 Perfomance

- Kapazität
- Bandbereitenbedarf
- antwortzeiten
speicherbedarf etc.
- app perfomance

#### 3.4.1 


### 3.5 Supportability

#### 3.5.1 Coding Standards
We are going to write the Code by using all of the most common clean code standards. For example we will name our variables and methods by their functionalities. This will keep the Code easy to read by everyone and make further developement much easier.

### 3.5.2 Testing Strategy
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
- Friend List - friends can be added and joined
- Profile - makes it possible to post information about yourself
- User Information - shows additional information about users (for example: Language, Country, Favourite Games, etc.)
- Settings - shows the Settings

#### 3.9.2 Hardware Interfaces
(n/a)

#### 3.9.3 Software Interfaces
The app will be runnable on Android 4.4 and higher. IOS won't be featured at the Moment.

#### 3.9.4 Communication Interfaces
The Server and Hardware will communicate using the http protocol. 

### 3.10 Licensing Requirements

### 3.11 Legal, Copyright, and Other Notices
The Logo is licensed to the Common Playground Team and is only allowed to use for the application. We do not take responsibilty for any incorrect data or errors in the application.

### 3.12 Applicable Standards

 Clean code
 DoD

## 4. Supporting Information
For any further information cou can contact the Common Playground Team or check our [Common Playground Blog](http://commonplayground.wordpress.com). 
The Team Members are:
- Celina Adam
- Inga Batton
- Nils Krehl 
- Denis Reibel
