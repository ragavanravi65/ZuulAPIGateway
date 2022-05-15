FROM openjdk:8-jdk-alpine
LABEL maintainer="Ragavan"
EXPOSE 8082
ARG JAR_FILE=build/libs/*.jar
ADD ${JAR_FILE} zuul.jar
ENTRYPOINT ["java","-jar","/zuul.jar"]