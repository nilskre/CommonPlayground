# Software Architecture Document

# Table of Contents
- [Introduction](#1-introduction)
    - [Purpose](#11-purpose)
    - [Scope](#12-scope)
    - [Definitions, Acronyms and Abbreviations](#13-definitions-acronyms-and-abbreviations)
    - [References](#14-references)
    - [Overview](#15-overview)
- [Architectural Representation](#2-architectural-representation)
- [Architectural Goals and Constraints](#3-architectural-goals-and-constraints)
- [Use-Case View](#4-use-case-view)
    - [Use-Case Realizations](#41-use-case-realizations)
- [Logical View](#5-logical-view)
    - [Overview](#51-overview)
    - [Architecturally Significant Design Packages](#52-architecturally-significant-design-packages)
- [Process View](#6-process-view)
- [Deployment View](#7-deployment-view)
- [Implementation View](#8-implementation-view)
    - [Overview](#81-overview)
    - [Layers](#82-layers)
- [Data View](#9-data-view)
- [Size and Performance](#10-size-and-performance)
- [Quality](#11-quality)

## 1. Introduction

### 1.1 Purpose
This document provides an overview of our software architecture. With several different architectural views it depicts different aspects of the system. It is intended to capture and convey the significant architectural decisions which have been made for the system.

### 1.2 Scope
This document describes the architecture of the CommonPlayground project.

### 1.3 Definitions, Acronyms and Abbreviations

| Abbrevation | Description                            |
| ----------- | -------------------------------------- |
| API         | Application programming interface      |
| MVC         | Model view controller                  |
| REST        | Representational state transfer        |
| SDK         | Software development kit               |
| SRS         | Software requirements specification    |
| UC          | Use case                               |
| VCS         | Version control system                 |
| n/a         | not applicable                         |
| tbd         | to be determined                       |

### 1.4 References

| Title                                                              		| Date       | Publishing organization   |
| --------------------------------------------------------------------------|:----------:| ------------------------- |
| [CommonPlayground Blog](https://commonplayground.wordpress.com/)   		| 2018-10-09 | CommonPlayground Team     |
| [Repository on GitHub](https://github.com/nilskre/CommonPlayground)		| 2018-10-09 | CommonPlayground Team     |
| [UC1 Posting a session](./use_cases/UC1_Post_Session.md)           		| 2019-04-17 | CommonPlayground Team     |
| [UC2 Joining a session](./use_cases/UC2_Join_Session.md)           		| 2019-04-17 | CommonPlayground Team     |
| [UC3 Getting an overview](./use_cases/UC3_Session_Overview.md)     		| 2019-04-17 | CommonPlayground Team     |
| [UC4 Creating an account](./use_cases/UC4_Create_Account.md)       		| 2019-04-17 | CommonPlayground Team     |
| [UC5 Logging in](./use_cases/UC5_Login.md)                         		| 2019-04-17 | CommonPlayground Team     |
| [UC6 Logout](./use_cases/UC6_Logout.md)                            		| 2019-04-17 | CommonPlayground Team     |
| [UC7 Keeping track of your sessions](./use_cases/UC7_Keeping_Track.md)  	| 2019-06-15 | CommonPlayground Team     |
| [UC8 Leaving a session](./use_cases/UC8_Leave_Session.md)                 | 2019-06-15 | CommonPlayground Team     |
| [UC9 Finding a session](./use_cases/UC9_Find_Session.md)                  | 2019-06-15 | CommonPlayground Team     |
| [UC10 Getting in touch](./use_cases/UC10_Getting_In_Touch.md)             | 2019-06-15 | CommonPlayground Team     |
| [Test plan](./test_plan/TestPlan.md)                                      | 2019-06-07 | CommonPlayground Team     |
| [SRS](./SoftwareRequirementsSpecification.md)                      		| 2019-06-14 | CommonPlayground Team     |

### 1.5 Overview
This document contains the architectural representation, goals and constraints as well 
as the logical, deployment, implementation and data views.

## 2. Architectural Representation
This project uses the MVC Pattern for the front end (Android App) and for the back end (Spring). So the model (data model, domain specific classes), the view (user interface) and the controller (controls the Application) are separated. The MVC Pattern can be seen in the next picture:

![MVC](./SAD_images/MVC.png)

https://www.techyourchance.com/wp-content/uploads/2015/06/MVC_MVP.png

The front end internally follows the MVVM pattern which can be depicted as following.
This pattern separates the View components again into a funcional part (the ViewModel) and a purely representational part (View) while the model remains analogous to the back end.
![MVVM](./SAD_images/MVVMPattern.png)
https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel

## 3. Architectural Goals and Constraints

### MVC
As mentioned in chapter two frontend and backend are using the MVC pattern. This enables a clean software architecture with separate model view and controller.

### Front end
The Android App Client is written in Java. In the Frontend no MVC Tool is needed, because the MVC Pattern is integrated into Android development.
However, since the App only serves as as front end the MVC it serves as the V component to the overall application formed by front end and back end together.
For a clean view structure the frontend in itself uses the MVVM (Model View ViewModel) pattern.
MVVM:
* Model: domain specific classes modeled after backend classes
* View: activities
* ViewModel: specific functionalities and network operations

### Back end
The back end is also written in Java. As MVC tool we use Spring Boot. For the account system Spring security is used. As a database we use H2. 
The Server offers multiple REST APIs which are accessed by our front end. 
MVC: 
* Model: domain specific classes
* View: no view available
* Controller: RestController

## 4. Use-Case View
![Overall-Use-Case-Diagram](./UseCaseDiagramCP.png)

### 4.1 Use-Case Realizations
n/a

## 5. Logical View

### 5.1 Overview
The logical view for our application follows the Spring Boot architecture and looks like:
![Spring Boot Backend](./SAD_images/spring_boot_logical_view.PNG)  
In our specific case the view however is not part of spring but provided separately as an android front end.
The android application handles all the user interaction and independently handles the view coordnation thus fulfilling the roles of view and dispatcher alike. However view and dispatcher do not interact with the client independently instead the dispatcher has been substituted by the ViewModel which connects the view and the model as describe above as well as forming the connection to the controller.
However the frontend does not interact with the model itself. Model classes are duplicated into the fronted for consistency reasons but are only used to populate the corresponding views.
Any actual manipulation of the model is handled by the backend.


### 5.2 Architecturally Significant Design Packages
On this section you can find our class diagrams for the front end and the back end. We have clearly marked which parts fulfill the model, the view and the controller tasks.

Here is the class diagram for the back end. As the backend has no view part we only highlighted the model and the controller parts.
![MVC Class Diagram Backend](./SAD_images/backend_class_diagram_mvc.png)

Here is the class diagram for the front end. The Frontend consists of the view, the ViewModel, and duplicated domain specific classes from the back end (model).
![MVC Class Diagram Frontend](./SAD_images/frontend_class_diagram_mvc.png)


## 6. Process View
n/a

## 7. Deployment View
Here you can see our deployement view diagram:
![Deployement View](./SAD_images/deployment_view.png)

## 8. Implementation View
n/a
### 8.1 Overview
n/a
### 8.2 Layers
n/a

## 9. Data View
Database ER-Diagram:

![Database ER-Diagram](./database_scheme/2018-11-11_database_scheme_.png)

## 10. Size and Performance
n/a

## 11. Quality/Metrics
The application is being measured in terms of complexity, coupling and cohesion. Due to the MVC Pattern the backend is unproblematic regarding any of these metrics. The Android framework makes it more difficult to achieve similarly good metrics for the frontend. Handling the UI elements requires many method calls from framework classes, contexts and views have to be handled and passed which increases all of the above mentioned metrics. However we have commited to still avoid high ratings in these categories even though we could not prevent several classes to be rated medium-high.
One measurment to achieve this is the use of patterns. We implemented the template method pattern to handle the frontend http requests. We created two templates which contain the sending of the two requests types (JSON and string). We then created specific request classes which extend from the templates and which implement their own specific ways to handle the response as the sending is the same for each request but the reponses have to be dealt with individually. This way we avoid duplicating the common parts.
![Pattern Diagram](./class_diagrams/Frontend_With_Pattern.png)
