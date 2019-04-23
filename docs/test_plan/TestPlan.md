# Test plan

## Table of Contents
|-------|----------------|
| 1.    | Introduction   |     
| 1.1   | Purpose |
| 1.2   | Scope |
| 1.3   | References |
| 1.3   | Intended Audience |
| 1.4   | Background |
| 1.5   | Evaluation Mission |
| 1.6   | Test Motivators |
| 1.7   | Target Test Items |
| 2.    | Outline of Planned Tests |
| 2.1   | Outline of Test Inclusions |
| 2.2   | Outline of Other Candidates for Potential Inclusion |
| 3.    | Test Approach |
| 3.1   | Testing Techniques and Types |
| 3.1.1 | Function Testing |
| 3.1.2 | Unit Testing |
| 3.1.3 | Performance Profiling |
| 4.    | Entry and Exit Criteria |
| 4.1   | Test Plan |
| 4.1.1 | Test Plan Entry Criteria |
| 4.1.2 | Test Plan Exit Criteria |
| 4.2   | Deliverables |
| 4.2.1 | Test Evaluation Summaries |
| 4.2.2 | Reporting on Test Coverage |
| 4.2.3 | Perceived Quality Reports |
| 5.    | Testing Workflow |
| 6.    | Environmental Needs |
| 6.1   | Base System Hardware |
| 6.2   | Base Software Elements in the Test Environment |
| 6.3   | Productivity and Support Tools |
| 6.4   | Test Environment Configurations |
| 7.    | Responsibilities, Staffing, and Training Needs |
| 7.1   | People and Roles |
| 8.    | Iteration Milestones |

## 1. Introduction
### 1.1 Purpose
The purpose of the Iteration Test Plan is to gather all of the information necessary to plan and control the test effort for a given iteration. 
It describes the approach to testing the software.
This Test Plan for CommonPlayground supports the following objectives:

- Identifies the items that should be targeted by the tests.
- Identifies the motivation for and ideas behind the test areas to be covered.
- Outlines the testing approach that will be used.
- Identifies the required resources and provides an estimate of the test efforts.

### 1.2 Scope
This test plan will cover tests assuring the functionality of the application's front end, back end and the communication between the two.
This document shows the following types of testing:

 - Unit Testing
 - User Interface Testing
 - API Testing

 Not covered are any tests related to performance and scale or usability.
 
### 1.3 Intended Audience
This test plan is written primarily for internal documentation reasons. It is meant to provide orientation to the developers to work from and as a documentation to measure the fullfillment of quality requirements against.

### 1.4 Document Terminology and Acronyms

| Abbr | Abbreviation                        |
|------|-------------------------------------|
| API  | Application Programmable Interface  |
| CI   | Continuous Integration              |
| n/a  | not applicable                      |
| SRS  | Software Requirements Specification |
| tbd  | to be determined                    |
| UI   | User Interface                      |
| VC   | Version Control                     |

### 1.5  References

| Title                                                                | Date       | Publishing organization   |
| ---------------------------------------------------------------------|:----------:| ------------------------- |
| [Blog](https://commonplayground.wordpress.com)                       | Oct. 2018  | PinguCrew                 |
| [GitHub - Server](https://github.com/nilskre/CommonPlayground)       | Oct. 2018  | PinguCrew                 |
| [UC1 Posting a Session](../use_cases/UC1_Post_Session.md)            | Oct. 2018  | PinguCrew                 |
| [UC2 Joining a Session](../use_cases/UC2_Join_Session.md)            | Oct. 2018  | PinguCrew                 |
| [UC3 Session Overview](../use_cases/UC3_Session_Overview.md)         | Oct. 2018  | PinguCrew                 |
| [UC4 Create an Account](../use_cases/UC4_Create_Account.md)          | Oct. 2018  | PinguCrew                 |
| [UC5 Login](../use_cases/UC5_Login.md)                               | Oct. 2018  | PinguCrew                 |
| [UC6 Logout](../use_cases/UC6_Logout.md)                             | Nov. 2018  | PinguCrew                 |
| [UC7 Keeping Track of Your Sessions](../use_cases/UC7_Keeping_Track.md) | Apr. 2019  | PinguCrew                 |
| [UC8 Leaving a Session](../use_cases/UC8_Leave_Session.md)           | Apr. 2019  | PinguCrew                 |
| [UC9 Finding a Session](../use_cases/UC9_Leave_Session.md)           | Apr. 2019  | PinguCrew                 |
| [Test Plan](./TestPlan.md)                                                      | Apr. 2019  | PinguCrew                 |
| [SRS](../SoftwareRequirementsSpecification.md)                       | Oct. 2018  | PinguCrew                 |

### 1.6 Document Structure
n/a

## 2. Evaluation Mission and Test Motivation
### 2.1 Background
Testing serves to ensure that the written code does what it is intended to do. It also prevents future code changes to break existing functionality unnoticed. In the context of integration it can also prevent broken software states to be merged into secured VC branches

### 2.2 Evaluation Mission
Testing is a crucial phase in the development cycle. It is necessary in order to fix technical bugs and important functional problems. With TDD we define the test first and can fix bugs before they even occur.

### 2.3 Test Motivators
The tests are done to ensure quality and mitigate risks and fulfill functional requirements. Their purpose is to provide stability for our application.

## 3. Target Test Items

- Android front end
- Server back end
- APIs

## 4. Outline of Planned Tests
### 4.1 Outline of Test Inclusions

*Android Client*:
 - UI testing of views/fragments

*Server*:

*APIs*:

GO Nils

The tests themself will not be tested and will not account into code coverage.

### 4.2 Outline of Other Candidates for Potential Inclusion
n/a 

### 4.3 Outline of Test Exclusions
Because of time and resource constraints we will not do:
- Stress test
- Load/performance tests
- Usability tests
- any further tests

## 5. Test Approach

### 5.1 Testing Techniques and Types

#### 5.1.1 Unit Testing
The primary goal of unit testing is to take the smallest piece of testable code of the application, isolate it from the remainder of the code, and determine whether it behaves exactly as you expect. Each unit is tested separately before integrating them into modules to test the interfaces between modules. Unit testing has proven its value in that a large percentage of defects are identified during its use.  
Our project uses JUnit for unit testing the server.

|                       | Description                                                         |
|-----------------------|---------------------------------------------------------------------|
|Technique Objective    | Ensure that the implemented code works properly and independently.  |
|Technique              | Implement test methods using JUnit Framework and its annotations / using `go test`.     |
|Oracles                | Console Output, Logs (TravisCI/TeamCity), Calculated Code Coverage by Codecov/TeamCity  |
|Required Tools         | Test Runner: AndroidStudio with Gradle / GoLand                     |
|Success Criteria       | All tests pass. Coverage is above 10% (client) / 60% (server)       |
|                       | Android: [TeamCity](https://teamcity.ameyering.de/viewType.html?buildTypeId=WgPlaner_BuildClient&guest=1), [TravisCI](https://travis-ci.org/WGPlaner/wg_planer) |
|                       | Server: [TravisCI](https://travis-ci.org/WGPlaner/wg_planer_server) |
|Special Considerations | -                                                                   |

#### 5.1.2 User Interface Testing
UI testing verifies a user's interaction with the software. The goal of UI testing is to ensure that the UI provides the user with the appropriate access and navigation through the functions of the target-of-test. In addition, UI testing ensures that the objects within the UI work as expected and conform to corporate or industry standards.

For our automated UI tests we use Espresso und Mockito.

|                       | Description                                                          |
|-----------------------|----------------------------------------------------------------------|
|Technique Objective    | Exercise UI access (clicking buttons, entering text into fields,...) |
|Technique              | Writing Gherkin `.feature` files to specify steps and expected outcome. User Interactions (mouse clicks, keyboard inputs, browser navigation) are emulated. After that the tool compares the interface's actual appearance with the expected one. If a discrepancy is detected, the tool will report an error. [Click here for more information](https://wgplanerblog.wordpress.com/2017/11/12/week-6-gherkin-feature-files/) |
|Oracles                | Test is successful if the graphical user interface has adopted a specific state. This state's occurance can be detected by automated checking for GUI elements like butons, labels, inputs, etc. |
|Required Tools         | Test Runner: AndroidStudio (Mockito + Espresso)                      |
|Success Criteria       | All UI tests pass.                                                   |
|Special Considerations | UI tests require an Android Emulator. This is not available for TravisCI/TeamCity |

#### 5.1.3 API Testing
The primary goal of integration testing is to ensure that the server handles requests in a proper way.  
Descripe Tests

|                       | Description                                                          |
|-----------------------|----------------------------------------------------------------------|
|Technique Objective    | Test each server API call (including auth checks, etc.)              |
|Technique              | Write `go` integration tests that use fixtures and test each API call independently. |
|Oracles                | Console Output, Logs (TravisCI), Calculated Code Coverage by Codecov |
|Required Tools         | `go test`, [stretchr](https://github.com/stretchr/testify), [testfixtures](https://github.com/go-testfixtures/testfixtures/tree/v2.4.3) |
|Success Criteria       | All tests pass. Coverage is above 60%                                |
|                       | Server: [TravisCI](https://travis-ci.org/WGPlaner/wg_planer_server)  |
|Special Considerations | -                                                                    |


## 6. Entry and Exit Criteria
### 6.1 Test Plan

#### 6.1.1 Test Plan Entry Criteria
n/a

#### 6.1.2 Test Plan Exit Criteria
n/a

## 7. Deliverables

## 7.1 Test Evaluation Summaries
The project (client + server) has over 100 tests which are passing. We use multiple continuous integration services to build our client and server and to run tests. These are:

|            | Continuous Integration Service                              | Badge |
|------------|-------------------------------------------------------------|:-----:|
| **Client** | [TeamCity](https://teamcity.ameyering.de/viewType.html?buildTypeId=WgPlaner_BuildClient&guest=1) | [![Build Status TeamCity](https://teamcity.ameyering.de/app/rest/builds/buildType:(id:WgPlaner_BuildClient)/statusIcon)](https://teamcity.ameyering.de/viewType.html?buildTypeId=WgPlaner_BuildClient&guest=1) |
|            | [TravisCI](https://travis-ci.org/WGPlaner/wg_planer)        | [![Build Status Travis](https://travis-ci.org/WGPlaner/wg_planer.svg?branch=master)](https://travis-ci.org/WGPlaner/wg_planer) |
| **Server** | [TravisCI](https://travis-ci.org/WGPlaner/wg_planer_server) | [![Build Status Travis](https://travis-ci.org/WGPlaner/wg_planer_server.svg?branch=master)](https://travis-ci.org/WGPlaner/wg_planer_server) |
|            | [AppVeyor](https://www.appveyor.com/docs/) <br/> (*on hold because a framework does not support windows) | [![Build Status AppVeyor](https://ci.appveyor.com/api/projects/status/ok5rq84eh6sx8lxd/branch/master?svg=true)](https://ci.appveyor.com/project/archer96/wg-planer-server/branch/master) |

The first picture shows our server tests running in GoLand. The second shows the build history of the server on TravisCI and the third the build history of our client on TeamCity.

![GoLand test runner](../Blog/img/codecov/GoLand_TestRunner.png)
![TravisCI WGPlaner server](../Blog/img/codecov/TravisCI_Server.png)
![TeamCity Client history](../Blog/img/codecov/TeamCity_History.png)

## 7.2 Reporting on Test Coverage
After a server build on TravisCI was successful, the resulting coverage report by `go test` is uploaded to [codecov.io](https://codecov.io/gh/WGPlaner/wg_planer_server). Following badge shows the current server test coverage:

![codecov badge](https://camo.githubusercontent.com/255e5ef101e034a3528267072eb16827a66ede7b/68747470733a2f2f636f6465636f762e696f2f67682f5747506c616e65722f77675f706c616e65725f7365727665722f6272616e63682f6d61737465722f67726170682f62616467652e737667)

For our android client we use jacoco to calculate the test coverage. TeamCity creates a simple code coverage overview for each build. Following link gives a good example: [TeamCity Coverage](https://teamcity.ameyering.de/viewLog.html?buildId=427&tab=buildResultsDiv&buildTypeId=WgPlaner_BuildClient).

## 7.3 Perceived Quality Reports
The tool which is used for quality reports for both server and client is Codacy. It shows the number of errors in the code as well as several other metrics. Furthermore we activated the codacy GitHub bot. It writes reports for each pull request. The developer has to fix these before merging is allowed.

Furthermore we use the [Go Report Card](https://goreportcard.com/) to check for common mistakes and formatting issues. This report is better suited for projects written in `go` than codacy.

Following badges show the current report status:

|             | Badge |
|-------------|:-----:|
| Server      | [![Codacy Badge](https://api.codacy.com/project/badge/Grade/492b2e1e8ce9415fa826016952baaa15)](https://www.codacy.com/app/archer96/wg_planer_server?utm_source=github.com&utm_medium=referral&utm_content=WGPlaner/wg_planer_server&utm_campaign=badger) |
|             | [![Go Report Card](https://goreportcard.com/badge/github.com/wgplaner/wg_planer_server)](https://goreportcard.com/report/github.com/wgplaner/wg_planer_server) |
| Client      | [![Codacy Badge](https://api.codacy.com/project/badge/Grade/566d7b24441a47d1a0b360f1e18d9ae0)](https://www.codacy.com/app/archer96/wg_planer?utm_source=github.com&utm_medium=referral&utm_content=WGPlaner/wg_planer&utm_campaign=badger) |

On Android we use SonarQube as well. It shows the amount of issues in the code as well as several metrics.
SonarQube reports can be found on  [sonarcloud.io](https://sonarcloud.io/dashboard?id=wg_planer%3Aapp).

## 7.4 Incident Logs and Change Requests
We integrated the tools mentioned above into our GitHub pull request workflow. If a build fails (or a reviewer dismisses the PR), merging the PR is blocked. See the screenshot below:

![GitHub PR blocked](../Blog/img/codecov/GitHub_Blocked_PR.png)

## 7.5 Smoke Test Suite and Supporting Test Scripts
For each pull request on the server project, a codecov report is generated that shows the new test coverage. This allows to check for any regressions.  
The screenshot below is an example of mentioned reports:

![Codecov Report](../Blog/img/codecov/Codecov_Report_GitHub.png)

## 8. Testing Workflow
Every developer can run tests out of his or her IDE manually. To check if the developed code is compatible with the existing code.

At the moment tests are automatically run before deployment on the server (see section 7.1).

## 9. Environmental Needs

### 9.1 Base System Hardware
The following table sets forth the system resources for the test effort presented in this Test Plan.

| Resource              | Quantity | Name and Type                |
|-----------------------|:--------:|------------------------------|
| Server                |    1     | api.wgplaner.ameyering.de    |
| Database              |    1     | api.wgplaner.ameyering.de    |
| Client Test device    |    1     | Android device (Andre, Arne) |

### 9.2 Base Software Elements in the Test Environment
The following base software elements are required in the test environment for this Test Plan.

| Software Element Name |  Type and Other Notes                        |
|-----------------------|----------------------------------------------|
| JUnit                 | Unit Testing library                         |
| Android Studio        | Local Test Runner / IDE                      |
| GoLand                | Local Test Runner / IDE                      |
| go tools              | language tools (for testing, coverage, etc.) |

### 9.3 Productivity and Support Tools
The following tools will be employed to support the test process for this Test Plan.

| Tool Category or Type | Tool Brand Name                              |
|-----------------------|----------------------------------------------|
| Hoster                | [netcup.de](https://netcup.de)               |
| Code Hoster           | [github.com](http://github.com/)             |
| Test Coverage Monitor | [codecov.io](https://codecov.io)             |
| CI Service            | [TeamCity](http://teamcity.ameyering.de/)    |
| CI Service            | [Travis CI](http://travis-ci.org/)           |
| CI Service            | [Appveyor](https://www.appveyor.com/docs/)   |
| Metrics Tool          | [codacy](https://app.codacy.com/)            |
| Metrics Tool          | [Go Report Card](https://goreportcard.com/)  |

## 10. Responsibilities, Staffing, and Training Needs

### 10.1 People and Roles
This table shows the staffing assumptions for the test effort.

| Role          | Minimum Resources Recommended<br>(number of full-time roles allocated) |  Specific Responsibilities or Comments |
|---------------|:----------------------------------------------------------------------:|----------------------------------------|
| Test Manager  | 1 | Provides management oversight. Responsibilities include: <br> - planning and logistics <br> - agree mission <br> - identify motivators<br> - acquire appropriate resources<br> - present management reporting<br> - advocate the interests of test<br> - evaluate effectiveness of test effort |
| Test Designer | 1 | Defines the technical approach to the implementation of the test effort. Responsibilities include:<br> define test approach<br> define test automation architecture<br> verify test techniques<br> define testability elements<br> structure test implementation |
| Tester        | 1 |  Implements and executes the tests. Responsibilities include:<br> - implement tests and test suites<br> execute test suites<br> - log results<br> - analyze and recover from test failures<br> - document incidents|
| Test System Administrator | 1 | Ensures test environment and assets are managed and maintained. Responsibilities include:<br>     administer test management system<br> - install and support access to, and recovery of, test environment configurations and test labs | 
| Implementer   | 2 | Implements and unit tests the test classes and test packages. Responsibilities include:<br> - creates the test components required to support testability requirements as defined by the designer |

### 10.2 Staffing and Training Needs
n/a

## 11. Iteration Milestones

We want to keep over 20% code coverage.

## 12. Risks, Dependencies, Assumptions, and Constraints

| Risk | Mitigation Strategy | Contingency (Risk is realized) |
|------|---------------------|--------------------------------|
| Dependency Injection impedes isolated Unit Testing | Abstract into new unit | transfer method into new scope |
| Unit test cannot be applied because of framework structure | Pay attention to feature and code linkage | Test via Integration test |
