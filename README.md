# API Gateway

> [!IMPORTANT]
> This service acts as the central entry point and reverse proxy for all microservices in the Futebol 2026 platform using Spring Cloud Gateway. Direct access to downstream microservices is restricted.

---

## :book: Table of Contents

* [Overview](https://www.google.com/search?q=%23overview)
* [Architecture & Routing](https://www.google.com/search?q=%23architecture--routing)
* [Prerequisites](https://www.google.com/search?q=%23prerequisites)
* [Installation & Setup](https://www.google.com/search?q=%23installation--setup)
* [Configuration](https://www.google.com/search?q=%23configuration)
* [Project Roadmap](https://www.google.com/search?q=%23project-roadmap)
* [Contributing](https://www.google.com/search?q=%23contributing)

---

## :mag: Overview

The **Futebol 2026 API Gateway** routes incoming client requests to appropriate internal Python microservices (Clubs, Players, and Matches). Built with Spring Boot and Spring Cloud Gateway, it provides reactive and non-blocking request handling, filter management, and centralized traffic control.

> [!NOTE]
> All incoming traffic flows through this gateway instance before reaching backend domain nodes.

### Key Features

* **Reactive Routing:** Built on Spring WebFlux for high performance and non-blocking I/O.
* **Dynamic Predicates:** Seamless path matching and route redirection to backend services.
* **Filter Integration:** Global and route-specific filters for request modification and header injection.

---

## :triangular_ruler: Architecture & Routing

| Prefix Route | Target Service | Authentication Required | Description |
| --- | --- | --- | --- |
| `/api/v1/jogadores` | `jogador-service` | Optional / Gateway Filter | Player management and market transactions |
| `/api/v1/clubes` | `clube-service` | Optional / Gateway Filter | Club rosters, budgets, and institutional data |
| `/api/v1/jogos` | `jogos-service` | Optional / Gateway Filter | Match schedules, scores, and fixture results |

> [!WARNING]
> Updating route definitions in `application.yml` requires restarting the gateway instance to apply changes.

---

## :computer: Prerequisites

Ensure you have the following installed on your machine:

* Java JDK 17 or 21
* Maven (or use the included Maven Wrapper `./mvnw`)
* Docker & Docker Compose (for containerized deployments)

> [!TIP]
> Use SDKMAN! or a similar tool to manage Java Development Kit versions smoothly across environments.

---

## :rocket: Installation & Setup

1. **Clone the repository:**
```bash
git clone https://github.com/projetosoftware-2026-futebol/gateway.git
cd gateway

```


2. **Build the project using Maven:**
```bash
./mvnw clean install

```


3. **Configure environment variables or application properties:**
Update downstream service URIs in `src/main/resources/application.yml` or set environment variables:
```env
SERVER_PORT=8080
JOGADOR_SERVICE_URL=http://localhost:8001
CLUBE_SERVICE_URL=http://localhost:8002
JOGOS_SERVICE_URL=http://localhost:8003

```


4. **Run the application:**
```bash
./mvnw spring-boot:run

```



> [!CAUTION]
> Do not commit sensitive credentials or production database connections into version control.

---

## :gear: Configuration

```yaml
server:
  port: 8080

spring:
  cloud:
    gateway:
      routes:
        - id: jogador-service
          uri: http://localhost:8001
          predicates:
            - Path=/api/v1/jogadores/**
        - id: clube-service
          uri: http://localhost:8002
          predicates:
            - Path=/api/v1/clubes/**
        - id: jogos-service
          uri: http://localhost:8003
          predicates:
            - Path=/api/v1/jogos/**

```
