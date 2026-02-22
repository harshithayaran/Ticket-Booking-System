package com.example.ticketBooking.dto;

import com.example.ticketBooking.model.User;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class TicketResponse {

    private Long id;
    private String movieName;
    private String seatNumber;
    private LocalDateTime movieTime;
    private LocalDateTime bookingTime;
    private double price;
    private String status;

}
