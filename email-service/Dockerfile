# Etapa 1: build do jar (imagem já vem com Maven + JDK), sem o maven tem que rodar manualmente a cria çã odo jar com:
# Se for criar o arquivo *.jar manualmente, ignore a Etapa 1
# mvn clean package -DskipTests
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: runtime
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Exponha a porta do Spring Boot (seu service)
EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]

# tags[simple tags] openjdk = https://hub.docker.com/_/openjdk

# Passos no CMD
# 1. Criar a imagem docker
# 2. Rodar o container

# docker build -t email-service .
# docker run -p 8083:8083 email-service
# PORTA_HOST(pc) : PORTA_CONTAINER
