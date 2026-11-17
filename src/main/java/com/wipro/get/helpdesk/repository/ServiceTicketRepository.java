package com.wipro.get.helpdesk.repository;

import com.wipro.get.helpdesk.entity.ServiceTicket;
import com.wipro.get.helpdesk.entity.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceTicketRepository extends JpaRepository<ServiceTicket, Long> {
    List<ServiceTicket> findByStatus(TicketStatus status);
    List<ServiceTicket> findByRequesterEmailIgnoreCase(String requesterEmail);
}
