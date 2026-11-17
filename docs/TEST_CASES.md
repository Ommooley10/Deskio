# Test Cases

| ID | Type | Scenario | Expected Result |
|---|---|---|---|
| TC-001 | Unit | Create valid ticket | Ticket created with OPEN status |
| TC-002 | Unit | Fetch missing ticket | TicketNotFoundException |
| TC-003 | Unit | Update status/assignee | Fields updated |
| TC-004 | API | Valid POST /tickets | HTTP 201 |
| TC-005 | API | Invalid requester email | HTTP 400 |
| TC-006 | DB | Query by status | Matching rows returned |
| TC-007 | API | Get unknown ID | HTTP 404 |
| TC-008 | API | Delete ticket | HTTP 204 |

## Expected vs Actual

The Postman collection contains automated assertions for expected HTTP status codes and response fields, so actual execution results are captured by the Postman test runner.
