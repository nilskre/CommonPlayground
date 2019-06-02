# CommonPlayground

![CommonPlayground Logo](/docs/CP_Logo.png)

[Blog](https://commonplayground.wordpress.com/)  
[YouTrack](https://commonplayground.myjetbrains.com/youtrack/issues)

## Vision

Inspired by carpool coordination services like ‘BlaBlaCar’ or ‘Mitfahrzentrale’ we want to build an application to coordinate game sessions. We plan to create a platform for people who are looking for other people to play games with. Covering online multiplayer games, tabletop, pen and paper or regular board games we want to provide a kind of bulletin board where people can state what they want to play, when and where they want to do it and how many people they are looking for. Others can then react to the postings and virtually join the play session to be connected by us so everyone can coordinate the actual play session together on a Common Playground.

## Badges

| | |
|---------------------|---|
| Build status master | [![Build Status](https://travis-ci.com/nilskre/CommonPlayground.svg?branch=master)](https://travis-ci.com/nilskre/CommonPlayground)  |
| Build status dev    | [![Build Status](https://travis-ci.com/nilskre/CommonPlayground.svg?branch=dev)](https://travis-ci.com/nilskre/CommonPlayground)  |
| Codacy Grade        | [![Codacy Badge](https://api.codacy.com/project/badge/Grade/7fdcfeca10b94f4c9b6bc1a809669c2b)](https://www.codacy.com/app/CommonPlayground/CommonPlayground?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=nilskre/CommonPlayground&amp;utm_campaign=Badge_Grade)  |
| Code Test Coverage  | [![Codacy Badge](https://api.codacy.com/project/badge/Coverage/7fdcfeca10b94f4c9b6bc1a809669c2b)](https://www.codacy.com/app/CommonPlayground/CommonPlayground?utm_source=github.com&utm_medium=referral&utm_content=nilskre/CommonPlayground&utm_campaign=Badge_Coverage)  |

## Installation

### Backend

Execute: ```docker run -p 8080:8080 commonplayground/commonplayground```  
This will download the latest Docker container from DockerHub and executes the Spring Boot backend on ```http://localhost:8080```

### Frontend

Allow installation of apps from unknown sources. Then download our latest release and install it: [Releases](https://github.com/nilskre/CommonPlayground/releases)

If it isn't working the first time, then install a older version first, and uninstall it again. Then you should be able to install the latest release.
