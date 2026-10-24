# Football Platform API Gateway

## Overview

The **API Gateway** serves as the single entry point for the football management platform ecosystem. It is responsible for request routing, payload validation, authentication verification, and proxying incoming client requests to their respective backend microservices.

> [!NOTE]
> This service abstracts internal service topology from external clients, ensuring secure and centralized traffic management across the entire architecture.

---

## Architecture & Core Responsibilities

* **Request Routing:** Forwards incoming HTTP requests to downstream services based on URL path prefixes.
* **Authentication & Authorization:** Validates JSON Web Tokens (JWT) before proxying requests to protected routes.
* **Rate Limiting & Security:** Protects downstream microservices from denial of service attacks and brute force attempts.
* **Error Handling:** Provides centralized error logging and standardized error response formats for client applications.

---

## Tech Stack

* **Language/Runtime:** Node.js / Python *(Update based on your exact implementation)*
* **Framework:** Express / FastAPI
* **Containerization:** Docker & Docker Compose
* **Proxy Middleware:** `http-proxy-middleware` / Reverse proxy configuration

---

## Getting Started

### Prerequisites

Ensure you have the following installed on your local machine:

* Node.js (v18+ recommended) or Python (v3.10+)
* Docker and Docker Compose

### Installation

1. Clone the repository:
```bash
git clone https://github.com/projetosoftware-2026-futebol/gateway.git
cd gateway

```


2. Install dependencies:
```bash
npm install
# or if using Python:
pip install -r requirements.txt

```


3. Configure environment variables:
Create a `.env` file in the root directory based on the provided template:
```bash
cp .env.example .env

```


> [!IMPORTANT]
> Never commit your production `.env` file or secret keys to version control. Ensure all sensitive tokens are securely managed via environment variables.



---

## Environment Variables

| Variable | Description | Default |
| --- | --- | --- |
| `PORT` | Port on which the gateway listens | `3000` |
| `JWT_SECRET` | Secret key used for validating authorization tokens | `secret_key` |
| `MATCH_SERVICE_URL` | Base URL for the match management microservice | `http://localhost:3001` |
| `TEAM_SERVICE_URL` | Base URL for the team and player microservice | `http://localhost:3002` |

---

## Running the Application

### Local Development

To run the service in development mode with live reloading:

```bash
npm run dev
# or for Python:
uvicorn main:app --reload

```

### Docker Deployment

To spin up the gateway and its networked dependencies using Docker Compose:

```bash
docker-compose up --build

```

> [!WARNING]
> Ensure that all downstream microservice containers are running and accessible on the Docker network before routing production traffic through the gateway.

---

## Routing Reference

| Path Prefix | Target Microservice | Description |
| --- | --- | --- |
| `/api/v1/matches` | Match Service | Manages fixtures, scores, and live match events |
| `/api/v1/teams` | Team Service | Manages club rosters, player statistics, and lineups |
| `/api/v1/auth` | Auth Service | Handles user login, registration, and token generation |
