package com.wipro.get.helpdesk.dto;

import com.wipro.get.helpdesk.entity.Priority;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateTicketRequest(
        @NotBlank @Size(max = 120) String title,
        @NotBlank @Size(max = 500) String description,
        @NotBlank @Email @Size(max = 120) String requesterEmail,
        @NotNull Priority priority,
        @NotBlank @Size(max = 120) String category
) {}
