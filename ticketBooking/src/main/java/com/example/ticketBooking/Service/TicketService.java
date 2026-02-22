package com.example.ticketBooking.Service;

import com.example.ticketBooking.controller.TicketController;
import com.example.ticketBooking.dto.TicketDTO;
import com.example.ticketBooking.dto.TicketResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TicketService {
    List<TicketResponse> bookTicket(TicketDTO ticketDTO);
    TicketResponse cancelTicket(Long ticketId);
    List<TicketResponse> getMyTickets(String email);

    @PostMapping("ticket_app/booking/book")
    default List<TicketResponse> bookTicket(@Valid @RequestBody TicketDTO ticketDTO, TicketController ticketController){
        return bookTicket(ticketDTO);
    }
}
