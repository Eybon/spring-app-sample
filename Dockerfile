FROM eclipse-temurin:21-alpine
MAINTAINER github.com/Eybon

COPY backend/app/target/spring-sample-app-DEV-SNAPSHOT.jar app.jar

COPY bdd-fake bdd-fake

ENTRYPOINT ["java","-jar","/app.jar"]