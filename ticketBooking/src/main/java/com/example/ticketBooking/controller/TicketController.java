package com.example.ticketBooking.controller;

import com.example.ticketBooking.Service.TicketService;
import com.example.ticketBooking.dto.TicketResponse;
import com.example.ticketBooking.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;


@RestController
public class TicketController {

    TicketService ticketService;
    public TicketController(TicketService t){
        this.ticketService = t;
    }

    @PutMapping("ticket_app/booking/cancel/{ticketId}")
    public TicketResponse cancelTicket(@Valid @PathVariable Long ticketId){
        return ticketService.cancelTicket(ticketId);
    }

    @GetMapping("/ticket_app/booking/mytickets")
    public List<TicketResponse> getMyTickets(@AuthenticationPrincipal User user) {
        String email = user.getEmail();
        return ticketService.getMyTickets(email);
    }

}
