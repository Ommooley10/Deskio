package com.wipro.get.helpdesk.exception;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(Long id) {
        super("Ticket not found: " + id);
    }
}
