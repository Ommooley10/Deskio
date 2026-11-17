package com.wipro.get.helpdesk.service;

import com.wipro.get.helpdesk.dto.CreateTicketRequest;
import com.wipro.get.helpdesk.dto.TicketResponse;
import com.wipro.get.helpdesk.dto.UpdateTicketRequest;
import com.wipro.get.helpdesk.entity.Priority;
import com.wipro.get.helpdesk.entity.ServiceTicket;
import com.wipro.get.helpdesk.entity.TicketStatus;
import com.wipro.get.helpdesk.exception.TicketNotFoundException;
import com.wipro.get.helpdesk.repository.ServiceTicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class ServiceTicketServiceTest {

    @Mock
    ServiceTicketRepository repository;

    @InjectMocks
    ServiceTicketService service;

    @Test
    void create_shouldCreateOpenTicket() {
        CreateTicketRequest request = new CreateTicketRequest(
                "VPN issue", "Cannot connect to corporate VPN.",
                "user@example.com", Priority.HIGH, "Network"
        );

        ServiceTicket saved = new ServiceTicket();
        saved.setId(10L);
        saved.setTitle(request.title());
        saved.setDescription(request.description());
        saved.setRequesterEmail(request.requesterEmail());
        saved.setPriority(request.priority());
        saved.setCategory(request.category());
        saved.setStatus(TicketStatus.OPEN);

        when(repository.save(any(ServiceTicket.class))).thenReturn(saved);

        TicketResponse response = service.create(request);

        assertEquals(10L, response.id());
        assertEquals(TicketStatus.OPEN, response.status());
        verify(repository).save(any(ServiceTicket.class));
    }

    @Test
    void getById_shouldThrowWhenTicketDoesNotExist() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(TicketNotFoundException.class, () -> service.getById(99L));
    }

    @Test
    void update_shouldChangeStatusAndAssignee() {
        ServiceTicket ticket = new ServiceTicket();
        ticket.setId(5L);
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setPriority(Priority.MEDIUM);
        when(repository.findById(5L)).thenReturn(Optional.of(ticket));
        when(repository.save(any(ServiceTicket.class))).thenReturn(ticket);

        TicketResponse result = service.update(
                5L,
                new UpdateTicketRequest(TicketStatus.IN_PROGRESS, Priority.HIGH, "agent01")
        );

        assertEquals(TicketStatus.IN_PROGRESS, result.status());
        assertEquals(Priority.HIGH, result.priority());
        assertEquals("agent01", result.assignee());
        verify(repository).save(ticket);
    }
}
