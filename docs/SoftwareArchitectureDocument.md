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
This document provides an overview of our software architecture. With several different architectural views it depicts different aspects of the system. It is intended to capture and convey the significant architectural decisions which have been made on the system.

### 1.2 Scope
This document describes the architecture of the CommonPlayground project.

### 1.3 Definitions, Acronyms and Abbreviations

| Abbrevation | Description                            |
| ----------- | -------------------------------------- |
| API         | Application programming interface      |
| MVC         | Model View Controller                  |
| REST        | Representational state transfer        |
| SDK         | Software development kit               |
| SRS         | Software Requirements Specification    |
| UC          | Use Case                               |
| VCS         | Version Control System                 |
| n/a         | not applicable                         |
| tbd         | to be determined                       |

### 1.4 References

| Title                                                              | Date       | Publishing organization   |
| -------------------------------------------------------------------|:----------:| ------------------------- |
| [CommonPlayground Blog](https://commonplayground.wordpress.com/)   | 2018-10-09 | CommonPlayground Team     |
| [Repository on GitHub](https://github.com/nilskre/CommonPlayground)| 2018-10-09 | CommonPlayground Team     |
| [UC1 Posting a session](./use_cases/UC1_Post_Session.md)           | 2018-11-04 | CommonPlayground Team     |
| [UC2 Joining a session](./use_cases/UC2_Join_Session.md)           | 2018-10-28 | CommonPlayground Team     |
| [UC3 Getting an overview](./use_cases/UC3_Session_Overview.md)     | 2018-10-28 | CommonPlayground Team     |
| [UC4 Getting an overview](./use_cases/UC4_Session_Overview.md)     | 2018-11-04 | CommonPlayground Team     |
| [UC5 Logging in](./use_cases/UC5_Login.md)                         | 2018-11-12 | CommonPlayground Team     |
| [tbd Test Plan](../tbd)                                            | tbd 2018-XX-XX | CommonPlayground Team     |
| [SRS](./SoftwareRequirementsSpecification.md)                      | tbd 2018-10-14 | CommonPlayground Team     |

### 1.5 Overview
This document contains the Architectural Representation, Goals and Constraints as well 
as the Logical, Deployment, Implementation and Data Views.

## 2. Architectural Representation



## 3. Architectural Goals and Constraints
-[This section describes the software requirements and objectives that have some significant impact on the architecture, for example, safety, security, privacy, use of an off-the-shelf product, portability, distribution, and reuse. It also captures the special constraints that may apply: design and implementation strategy, development tools, team structure, schedule, legacy code, and so on.]


## 4. Use-Case View
![Overall-Use-Case-Diagram](./UseCaseDiagramCP.png)

### 4.1 Use-Case Realizations
tbd

## 5. Logical View

### 5.1 Overview

### 5.2 Architecturally Significant Design Packages



## 6. Process View
tbd
![Android Process View](./tbd)

## 7. Deployment View
tbd
![Deployment View](./tbd)

## 8. Implementation View

### 8.1 Overview

### 8.2 Layers


## 9. Data View
Database ER-Diagram:

![Database ER-Diagram](./database_scheme/2018-11-11_database_scheme_.png)

## 10. Size and Performance
n/a

## 11. Quality/Metrics
tbd
