package com.example.ticketBooking.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Ticket_Details",
        uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {"seatNumber", "movieDate", "movieName"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticket_id;

    @Column(nullable = false)
    private String movieName;

    @Column(nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    private LocalDateTime movieDate;

    @Column(nullable = false)
    private LocalDateTime bookingTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private double price;

    private String status;   // BOOKED, CANCELLED
}
