package com.wipro.get.helpdesk.dto;

import com.wipro.get.helpdesk.entity.Priority;
import com.wipro.get.helpdesk.entity.TicketStatus;
import com.wipro.get.helpdesk.entity.ServiceTicket;
import java.time.LocalDateTime;

public record TicketResponse(
        Long id,
        String title,
        String description,
        String requesterEmail,
        Priority priority,
        TicketStatus status,
        String category,
        String assignee,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static TicketResponse from(ServiceTicket t) {
        return new TicketResponse(
                t.getId(), t.getTitle(), t.getDescription(), t.getRequesterEmail(),
                t.getPriority(), t.getStatus(), t.getCategory(), t.getAssignee(),
                t.getCreatedAt(), t.getUpdatedAt()
        );
    }
}
