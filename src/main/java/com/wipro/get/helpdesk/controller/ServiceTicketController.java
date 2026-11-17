package com.wipro.get.helpdesk.controller;

import com.wipro.get.helpdesk.dto.CreateTicketRequest;
import com.wipro.get.helpdesk.dto.TicketResponse;
import com.wipro.get.helpdesk.dto.UpdateTicketRequest;
import com.wipro.get.helpdesk.entity.TicketStatus;
import com.wipro.get.helpdesk.service.ServiceTicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class ServiceTicketController {

    private final ServiceTicketService service;

    public ServiceTicketController(ServiceTicketService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TicketResponse> create(@Valid @RequestBody CreateTicketRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public TicketResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<TicketResponse> getAll(
            @RequestParam(required = false) TicketStatus status,
            @RequestParam(required = false) String requesterEmail) {
        return service.getAll(status, requesterEmail);
    }

    @PatchMapping("/{id}")
    public TicketResponse update(@PathVariable Long id, @Valid @RequestBody UpdateTicketRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
