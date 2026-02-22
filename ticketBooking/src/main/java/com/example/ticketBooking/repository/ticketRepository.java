package com.example.ticketBooking.repository;

import com.example.ticketBooking.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ticketRepository extends JpaRepository<Ticket, Long>  {
    List<Ticket> findByUserId(Long userId);  // fetch tickets per user

    Ticket findTicketBySeatNumber(String s);
}
