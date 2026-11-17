package com.wipro.get.helpdesk.repository;

import com.wipro.get.helpdesk.entity.Priority;
import com.wipro.get.helpdesk.entity.ServiceTicket;
import com.wipro.get.helpdesk.entity.TicketStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
class ServiceTicketRepositoryTest {

    @Autowired
    ServiceTicketRepository repository;

    @Test
    void findByStatus_shouldReturnMatchingTickets() {
        ServiceTicket ticket = new ServiceTicket();
        ticket.setTitle("Email outage");
        ticket.setDescription("Corporate email is unavailable.");
        ticket.setRequesterEmail("a@example.com");
        ticket.setPriority(Priority.CRITICAL);
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setCategory("Email");
        repository.save(ticket);

        assertEquals(1, repository.findByStatus(TicketStatus.OPEN).size());
    }
}
