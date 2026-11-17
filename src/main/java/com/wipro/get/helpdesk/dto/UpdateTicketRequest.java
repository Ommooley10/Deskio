package com.wipro.get.helpdesk.dto;

import com.wipro.get.helpdesk.entity.Priority;
import com.wipro.get.helpdesk.entity.TicketStatus;
import jakarta.validation.constraints.Size;

public record UpdateTicketRequest(
        TicketStatus status,
        Priority priority,
        @Size(max = 120) String assignee
) {}
