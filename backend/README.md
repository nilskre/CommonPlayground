#Readme

###1.Command-Line

Build: ./gradlew build

Run: ./gradlew bootRun

Run Tests: ./gradlew clean tests

Install Dependencies: ./gradlew clean


###2.Docker

Download: docker pull commonplayground/commonplayground
          
Images: docker image ls

Run: docker run -p 8080:8080 commonplayground/commonplayground

oder

Run: docker run -p 8080:8080<IMAGE_ID>

Build: ./gradlew build docker

Push: docker push commonplayground/commonplayground