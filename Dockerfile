# Stage 1: Criação da imagem final
FROM eclipse-temurin:17-jdk-alpine
COPY . .
RUN ./mvnw clean install -DskipTests
ENTRYPOINT ["java", "-jar", "target/Financial-Transfer-0.0.1-SNAPSHOT.jar"]