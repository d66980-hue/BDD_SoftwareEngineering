Docker Repository public link:
docker push saadahmedjaaml/spring-bdd_saadahmedjamal:tagname



## CI/CD Pipeline
# Spring BDD Service

This project uses GitHub Actions for CI/CD, triggered on every push to `master`.

### Pipeline Stages

1. **Build & Test** — Compiles the project and runs all tests against a temporary
   PostgreSQL instance spun up by GitHub Actions.
2. **Docker Build & Push** — Builds the Docker image and pushes it to Docker Hub
   with both a commit SHA tag and `latest`.
3. **Deploy** — SSHes into the server, pulls the latest image, and restarts the container.

### Configuration

All sensitive values are stored as GitHub Secrets (Settings → Secrets → Actions):

| Secret | Purpose |
|---|---|
| `DOCKER_USERNAME` | Docker Hub username |
| `DOCKER_PASSWORD` | Docker Hub password or access token |
| `DB_NAME` | PostgreSQL database name |
| `DB_USERNAME` | PostgreSQL username |
| `DB_PASSWORD` | PostgreSQL password |
| `DB_URL` | Full JDBC URL for production database |
| `SSH_HOST` | Deployment server hostname |
| `SSH_USERNAME` | SSH username |
| `SSH_PASSWORD` | SSH password |
| `SSH_PORT` | SSH port (default: 22) |

### Docker Image

https://hub.docker.com/saadahmedjaaml/spring-bdd_saadahmedjamal

### Running locally

```bash
docker pull /spring-bdd:latest
docker run -d -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/springbdd \
  -e SPRING_DATASOURCE_USERNAME=springuser \
  -e SPRING_DATASOURCE_PASSWORD=springpassword \
  /spring-bdd:latest
```


















Based on https://github.com/krushnaDash/spring-bdd


# spring-bdd

BDD-tested Spring Boot REST API using Cucumber, backed by MySQL in Docker and H2 for tests.

Based on https://github.com/krushnaDash/spring-bdd

---

## Project Structure

```
spring-bdd/
├── src/
│   ├── main/
│   │   └── resources/
│   │       └── application.properties      # Main config (env-var driven)
│   └── test/
│       ├── java/com/test/springbdd/
│       │   ├── StepDefinition.java          # Cucumber Spring context config
│       │   ├── CategoryStepDefination.java  # Category BDD steps (MockMvc)
│       │   ├── ProductServiceSteps.java     # Product BDD steps (REST Assured)
│       │   └── CucumberTest.java            # JUnit Cucumber runner
│       └── resources/
│           ├── application-test.properties  # H2 in-memory config for tests
│           └── *.feature                    # Cucumber feature files
├── Dockerfile                               # Multi-stage Docker build
├── docker-compose.yml                       # Full service stack definition
└── pom.xml
```

---

## docker-compose.yml structure

| Service | Image | Port | Role |
|---------|-------|------|------|
| `app` | Built from `Dockerfile` | `8080` | Spring Boot REST API |
| `db` | `mysql:8.0` | `3306` | Persistent MySQL database |

**Key behaviours:**
- `app` waits for `db` to pass its healthcheck before starting (`depends_on` + `condition: service_healthy`)
- `db` data is persisted in a named Docker volume `db_data` across restarts
- All credentials are passed to `app` via environment variables; no secrets are hardcoded in source
- `app` falls back to H2 when run locally without Docker

---

## Running with Docker Compose

**Prerequisites:** Docker Desktop (or Docker + Docker Compose plugin) installed.

```bash
# 1. Clone the repository
git clone 
cd spring-bdd

# 2. Build and start all services
docker-compose up --build

# 3. The API is available at
http://localhost:8080

# 4. Stop and remove containers (data volume is preserved)
docker-compose down

# 5. Stop and also delete the database volume
docker-compose down -v
```

---

## Running tests locally (without Docker)

Tests use an H2 in-memory database and do not require Docker:

```bash
mvn test
```
