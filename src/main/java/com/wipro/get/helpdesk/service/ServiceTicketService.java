package com.wipro.get.helpdesk.service;

import com.wipro.get.helpdesk.dto.CreateTicketRequest;
import com.wipro.get.helpdesk.dto.TicketResponse;
import com.wipro.get.helpdesk.dto.UpdateTicketRequest;
import com.wipro.get.helpdesk.entity.ServiceTicket;
import com.wipro.get.helpdesk.entity.TicketStatus;
import com.wipro.get.helpdesk.exception.TicketNotFoundException;
import com.wipro.get.helpdesk.repository.ServiceTicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceTicketService {

    private final ServiceTicketRepository repository;

    public ServiceTicketService(ServiceTicketRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public TicketResponse create(CreateTicketRequest request) {
        ServiceTicket t = new ServiceTicket();
        t.setTitle(request.title());
        t.setDescription(request.description());
        t.setRequesterEmail(request.requesterEmail());
        t.setPriority(request.priority());
        t.setCategory(request.category());
        t.setStatus(TicketStatus.OPEN);
        return TicketResponse.from(repository.save(t));
    }

    @Transactional(readOnly = true)
    public TicketResponse getById(Long id) {
        return repository.findById(id)
                .map(TicketResponse::from)
                .orElseThrow(() -> new TicketNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<TicketResponse> getAll(TicketStatus status, String requesterEmail) {
        List<ServiceTicket> tickets;
        if (status != null) {
            tickets = repository.findByStatus(status);
        } else if (requesterEmail != null && !requesterEmail.isBlank()) {
            tickets = repository.findByRequesterEmailIgnoreCase(requesterEmail);
        } else {
            tickets = repository.findAll();
        }
        return tickets.stream().map(TicketResponse::from).toList();
    }

    @Transactional
    public TicketResponse update(Long id, UpdateTicketRequest request) {
        ServiceTicket t = repository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));

        if (request.status() != null) t.setStatus(request.status());
        if (request.priority() != null) t.setPriority(request.priority());
        if (request.assignee() != null) t.setAssignee(request.assignee());

        return TicketResponse.from(repository.save(t));
    }

    @Transactional
    public void delete(Long id) {
        ServiceTicket t = repository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
        repository.delete(t);
    }
}
