package com.wipro.get.helpdesk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.get.helpdesk.dto.TicketResponse;
import com.wipro.get.helpdesk.dto.CreateTicketRequest;
import com.wipro.get.helpdesk.entity.Priority;
import com.wipro.get.helpdesk.entity.TicketStatus;
import com.wipro.get.helpdesk.service.ServiceTicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ServiceTicketController.class)
class ServiceTicketControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;

    @MockBean ServiceTicketService service;

    @Test
    void create_shouldReturn201ForValidRequest() throws Exception {
        TicketResponse response = new TicketResponse(
                1L, "Password reset", "Need account password reset.",
                "user@example.com", Priority.MEDIUM, TicketStatus.OPEN,
                "Access", null, null, null
        );
        when(service.create(any(CreateTicketRequest.class))).thenReturn(response);

        CreateTicketRequest request = new CreateTicketRequest(
                "Password reset", "Need account password reset.",
                "user@example.com", Priority.MEDIUM, "Access"
        );

        mockMvc.perform(post("/api/v1/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("OPEN"));
    }

    @Test
    void create_shouldReturn400ForInvalidEmail() throws Exception {
        CreateTicketRequest request = new CreateTicketRequest(
                "Password reset", "Need account password reset.",
                "invalid-email", Priority.MEDIUM, "Access"
        );

        mockMvc.perform(post("/api/v1/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("VALIDATION_ERROR"));
    }
}
