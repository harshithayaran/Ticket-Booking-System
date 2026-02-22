package com.example.ticketBooking.Exception;

public class TicketNotFoundException extends RuntimeException{
    public TicketNotFoundException(String msg){
        super(msg);
    }
}
