# Dockerfile para Eureka Server
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar eureka-server.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "eureka-server.jar"]

# Comandos para build:
# docker build -t seu-usuario-dockerhub/eureka-server:latest -f infra/discovery/Dockerfile .
# docker push seu-usuario-dockerhub/eureka-server:latest