FROM eclipse-temurin:21-jdk

# Metadados
LABEL maintainer="betofrasson"
LABEL description="API Gateway with Load Balancer for Blog Microservices"

# Criar diretório da aplicação
WORKDIR /app

# Copiar o Maven wrapper e pom.xml
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .
COPY .mvn .mvn

# Resolver dependências (cache layer)
RUN chmod +x ./mvnw && ./mvnw dependency:go-offline -B

# Copiar código fonte
COPY src src

# Build da aplicação
RUN ./mvnw clean package -DskipTests

# Usar uma imagem mais leve para produção
FROM eclipse-temurin:21-jre

# Instalar curl para health checks
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Criar diretório da aplicação
WORKDIR /app

# Copiar o JAR da aplicação
COPY --from=0 /app/target/*.jar app.jar

# Expor porta
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# Comando de inicialização
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]