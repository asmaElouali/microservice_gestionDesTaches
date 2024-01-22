FROM maven:3.9.6-openjdk-17 AS builder
WORKDIR /app
COPY ./src ./src
COPY ./pom.xml .
RUN mvn clean package

FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} gatway-service.jar
ENTRYPOINT ["java","-jar","/gatway-service.jar"]