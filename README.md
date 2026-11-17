# Enterprise Service Desk & Ticket Management System


## Tech Stack
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



