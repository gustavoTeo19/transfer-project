# Stage 1: Construção
FROM maven:3.6.3-jdk-11 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

# Stage 2: Criação da imagem final
FROM openjdk:11-jre-slim
COPY --from=build /usr/src/app/target/*.jar app.jar
CMD ["java", "-jar", "/app.jar"]