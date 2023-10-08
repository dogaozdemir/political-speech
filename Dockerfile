FROM openjdk:17-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:17-jdk-slim
WORKDIR political-speech
COPY --from=build target/*.jar political-speech.jar
ENTRYPOINT ["java", "-jar", "political-speech.jar"]