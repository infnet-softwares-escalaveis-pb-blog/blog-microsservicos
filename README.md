# Blog Microservices

Sistema de blog desenvolvido em arquitetura de microserviços utilizando Spring Boot e Spring Cloud.

## Arquitetura

O sistema é composto por:

- **API Gateway** - Gateway de entrada com roteamento e balanceamento de carga
- **Eureka Server** - Service Discovery para registro e descoberta de serviços
- **Blog Service** - Serviço principal para gerenciamento de posts e comentários
- **Email Service** - Serviço responsável pelo envio de emails

## Tecnologias

- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Cloud Gateway**
- **Netflix Eureka**
- **MongoDB** (para comentários do blog)
- **MySQL** (para blog e envio de e-mail)
- **Docker & Docker Compose**
- **Kubernetes**

## Executando o Projeto

```bash
# Deploy da infraestrutura
kubectl apply -f ../infra/
```

## Endpoints

- **API Gateway**: http://localhost:8080
- **Eureka Dashboard**: http://localhost:8761
- **Blog Service**: http://localhost:8081
- **Email Service**: http://localhost:8082
- **Login Service**: http://localhost:8083

## Monitoramento

O sistema inclui observabilidade com:

- **Zipkin** (tracing distribuído)
- **ELK Stack** (logs centralizados)
- **Actuator** (health checks)

---

## Equipe

### Grupo

- **Alberto Frasson**
- **Johanna Liza**
- **Samuel Hermany**
- **Uendel Ives**

### Professor

- **Leonardo Silva da Gloria**

### Instituição

**INSTITUTO INFNET**

**GRADUAÇÃO EM ENGENHARIA DE SOFTWARE**
