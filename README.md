# blog-microsservicos

# Instruções - Etapas

# 1 - Abra somente o projeto "post-service" no INTELIJ

# 2 - No "WSL" instalar o my sql rodando os comandos abaixo:
sudo apt update
sudo apt install mysql-client-core-8.0

# 3 - Remover container
docker-compose down

# 4 - Subir container
docker-compose up -d

# 5 - No "WSL" encontrar o endereço correto da aplicação no container
cat /etc/resolv.conf | grep nameserver

# 6 - Copiar o enderço informado:
172.23.80.1

# 7 - Colar o endereço acima em "application.properties"
spring.datasource.url=jdbc:mysql://172.23.80.1:3307/blogdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC

# 8 - Rodar no terminal do Intelij
mvn spring-boot:run
