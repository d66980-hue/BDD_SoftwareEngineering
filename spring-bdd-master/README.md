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
