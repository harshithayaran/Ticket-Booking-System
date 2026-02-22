package com.example.ticketBooking.ServiceIMPL;

import com.example.ticketBooking.Exception.InvalidBookingException;
import com.example.ticketBooking.Exception.SeatAlreadyBookedException;
import com.example.ticketBooking.Exception.TicketNotFoundException;
import com.example.ticketBooking.Exception.UserNotFoundException;
import com.example.ticketBooking.Service.TicketService;
import com.example.ticketBooking.dto.TicketDTO;
import com.example.ticketBooking.dto.TicketResponse;
import com.example.ticketBooking.model.Ticket;
import com.example.ticketBooking.model.User;
import com.example.ticketBooking.repository.ticketRepository;
import com.example.ticketBooking.repository.userRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ticketServiceIMPL implements TicketService {
    ticketRepository ticketR;

    userRepository userR;

    @Autowired
    public ticketServiceIMPL(ticketRepository ticketR, userRepository userR) {
        this.ticketR = ticketR;
        this.userR = userR;
    }

    @Override
    @Transactional
    public List<TicketResponse> bookTicket(TicketDTO ticketDTO) {
        User user = userR.findById(ticketDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        List<TicketResponse> responses = new ArrayList<>();
        for(Integer seat : ticketDTO.getSeatNumber()){

            Ticket checkSeat = ticketR.findTicketBySeatNumber(String.valueOf(seat));
            if(checkSeat != null && checkSeat.getStatus().equalsIgnoreCase("BOOKED") &&
                    checkSeat.getMovieName().equals(ticketDTO.getMovieName()) && checkSeat.getMovieDate().equals(ticketDTO.getMovieTime())){
                throw new SeatAlreadyBookedException("Seat " + String.valueOf(seat) + " is already booked");
            }

            Ticket ticket = new Ticket();
            ticket.setMovieDate(ticketDTO.getMovieTime());
            ticket.setMovieName(ticketDTO.getMovieName());
            ticket.setSeatNumber(String.valueOf(seat));
            ticket.setPrice(ticketDTO.getPrice());
            ticket.setBookingTime(LocalDateTime.now());
            ticket.setUser(user);
            ticket.setStatus("BOOKED");
            Ticket saved = ticketR.save(ticket);

            TicketResponse tr = new TicketResponse();
            tr.setId(saved.getTicket_id());
            tr.setMovieName(saved.getMovieName());
            tr.setSeatNumber(saved.getSeatNumber());
            tr.setBookingTime(saved.getBookingTime());
            tr.setPrice(saved.getPrice());
            tr.setStatus(saved.getStatus());
            tr.setMovieTime(saved.getMovieDate());
            responses.add(tr);
        }
        return responses;
    }

    @Override
    public TicketResponse cancelTicket(Long ticketId) {
        Ticket ticket = ticketR.findById(ticketId).orElseThrow(() -> new TicketNotFoundException("Ticket Not found"));
        if(LocalDateTime.now().isAfter((ticket.getMovieDate()))){
            throw new InvalidBookingException("Ticket can not be cancelled as movie has started/ended");
        }
        ticket.setStatus("CANCELLED");
        Ticket saved = ticketR.save(ticket);
        TicketResponse tr = new TicketResponse();
        tr.setId(saved.getTicket_id());
        tr.setMovieName(saved.getMovieName());
        tr.setMovieTime(saved.getMovieDate());
        tr.setSeatNumber(saved.getSeatNumber());
        tr.setBookingTime(saved.getBookingTime());
        tr.setPrice(saved.getPrice());
        tr.setStatus(saved.getStatus());

        return tr;
    }

    @Override
    public List<TicketResponse> getMyTickets(String email) {
        User user = userR.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        List<Ticket> tickets = ticketR.findByUserId(user.getId());

        List<TicketResponse> responses = new ArrayList<>();
        for (Ticket t : tickets) {
            TicketResponse tr = new TicketResponse();
            tr.setId(t.getTicket_id());
            tr.setMovieName(t.getMovieName());
            tr.setMovieTime(t.getMovieDate());
            tr.setSeatNumber(t.getSeatNumber());
            tr.setBookingTime(t.getBookingTime());
            tr.setPrice(t.getPrice());
            tr.setStatus(t.getStatus());
            responses.add(tr);
        }

        return responses;
    }
}
