package com.example.ticketBooking.dto;

import com.example.ticketBooking.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.validation.constraints.*;
@Getter
@Setter
public class TicketDTO {

    @NotBlank(message = "Movie name is required")
    private String movieName;
    @NotNull(message = "Movie time is required")
    @Future(message = "Movie time must be in future")
    private LocalDateTime movieTime;
    @NotNull(message = "Seat number is required")
    @Size(min = 1, message = "Select alteas 1 seat")
    private List<Integer> seatNumber;
    private LocalDateTime bookingTime;
    @Min(value = 1, message = "Price must be greater than 0")
    private double price;
    @NotNull(message = "User id is required")
    private Long userId;
}
