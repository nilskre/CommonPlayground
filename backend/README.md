# Readme

## 1.Command-Line

Build: ```./gradlew build```

Run: ```./gradlew bootRun```

Run Unit and Api tests:```./gradlew test clean```

Install Dependencies: ```./gradlew clean```

## 2.Docker

### Download and Run Docker container

Download latest Docker image: ```docker pull commonplayground/commonplayground```

Overview of all local Docker images: ```docker image ls```

Run: ```docker run -p 8080:8080 commonplayground/commonplayground```
or
Run: ```docker run -p 8080:8080<IMAGE_ID>```

### Build new Docker image and push it to DockerHub

Build: ```./gradlew build docker```

Push: ```docker push commonplayground/commonplayground```
