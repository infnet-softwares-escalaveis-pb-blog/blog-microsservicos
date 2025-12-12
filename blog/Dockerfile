FROM eclipse-temurin:21-jdk

# Metadados
LABEL maintainer="betofrasson"
LABEL description="Post Service for Blog Microservices"

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

# Criar usuário não-root para segurança
RUN groupadd -r appgroup && useradd -r -g appgroup appuser

# Criar diretório da aplicação
WORKDIR /app

# Copiar o JAR da aplicação
COPY --from=0 /app/target/*.jar app.jar

# Mudar ownership do diretório para o usuário da aplicação
RUN chown -R appuser:appgroup /app

# Expor porta
EXPOSE 8081

# Usar usuário não-root
USER appuser

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8081/actuator/health || exit 1

# Configurações de JVM para containers
ENV JAVA_OPTS="-Xms512m -Xmx1024m -XX:+UseG1GC -XX:MaxGCPauseMillis=200"

# Comando de inicialização
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/app.jar"]