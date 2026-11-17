# Enterprise Service Desk & Ticket Management System


## Tech Stack
a
- Java 17
- Spring Boot
- Spring Web / REST
- Spring Data JPA / Hibernate
- MySQL
- H2 for automated tests
- JUnit 5
- Mockito
- MockMvc
- Maven
- Swagger / OpenAPI
- Postman
- Docker / Docker Compose
- GitHub Actions CI

## Business Problem

An enterprise IT support team needs a centralized way to:

- Create service tickets
- Track ticket status and priority
- Assign tickets to support agents
- Search/filter tickets
- Validate API input
- Test business logic and persistence
- Verify APIs with automated API assertions
- Validate the database schema

## API Endpoints

| Method | Endpoint | Purpose |
|---|---|---|
| POST | /api/v1/tickets | Create a ticket |
| GET | /api/v1/tickets | List/filter tickets |
| GET | /api/v1/tickets/{id} | Get a ticket |
| PATCH | /api/v1/tickets/{id} | Update status/priority/assignee |
| DELETE | /api/v1/tickets/{id} | Delete ticket |
| GET | /swagger-ui.html | Swagger UI |

## 1. Start MySQL

```bash
docker compose up -d mysql
```

Default database:
- DB: enterprise_helpdesk
- User: helpdesk_user
- Password: helpdesk_password

## 2. Run the application

```bash
mvn clean test
mvn spring-boot:run
```

Application:
`http://localhost:8080`

Swagger:
`http://localhost:8080/swagger-ui.html`

OpenAPI JSON:
`http://localhost:8080/v3/api-docs`

## 3. Run tests

```bash
mvn test
```

Tests include:
- Service-layer unit testing with Mockito
- Controller/API testing with MockMvc
- JPA repository testing with H2
- Validation/error-path testing

## 4. Postman

Import:

`postman/Wipro_GET_Enterprise_Helpdesk.postman_collection.json`

The collection includes assertions for:
- Expected HTTP status
- Actual response fields
- Validation errors
- Ticket status updates
- Assignee updates

## 5. Database validation

Run:

`db/schema_validation.sql`

It checks:
- Table existence
- Required columns
- Primary key
- Required-column nullability

## 6. Build a Docker image

```bash
mvn clean package
docker build -t enterprise-helpdesk:1.0 .
docker run --rm -p 8080:8080 enterprise-helpdesk:1.0
```

Note: the container expects MySQL to be reachable via the configured DB environment variables when run outside the Compose network.

## Suggested Git workflow

```bash
git init
git add .
git commit -m "Initial enterprise helpdesk project"
git branch -M main
git remote add origin <your-github-repository>
git push -u origin main
```

## Interview talking points

1. Explain why a service layer sits between controller and repository.
2. Explain dependency injection in Spring.
3. Explain JPA entity mapping and why enums use `EnumType.STRING`.
4. Explain the difference between unit, integration, and API testing.
5. Explain Mockito mocks vs H2 repository tests.
6. Explain HTTP 201, 200, 204, 400, and 404.
7. Explain validation using `@Valid` and Bean Validation.
8. Explain how the CI workflow runs tests before packaging.
9. Explain why production systems should add authentication, authorization, audit logging, secrets management, and observability.

## Resume bullet

**Enterprise Service Desk & Ticket Management System | Java, Spring Boot, REST, MySQL, JPA, JUnit, Mockito, Swagger, Postman, Docker, GitHub Actions**

- Developed a layered Spring Boot REST API for enterprise ticket lifecycle management using Java 17, Spring Data JPA and MySQL.
- Implemented request validation, exception handling, filtering, status/priority workflows and Swagger/OpenAPI documentation.
- Built unit, controller/API and repository tests using JUnit 5, Mockito, MockMvc and H2; added Postman assertions for expected-vs-actual API validation.
- Added Docker Compose for MySQL and GitHub Actions CI to run automated tests and Maven builds.

## Production-hardening roadmap

For an actual enterprise deployment, add Spring Security/JWT, role-based access control, audit history, pagination, centralized logging, monitoring, secret management, database migrations with Flyway, integration-test containers, and deployment to a cloud platform.
