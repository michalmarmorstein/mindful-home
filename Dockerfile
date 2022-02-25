FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar mindful-home-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/mindful-home-0.0.1-SNAPSHOT.jar"]