package com.example.ticketBooking.Exception;

public class InvalidBookingException extends RuntimeException{
    public InvalidBookingException(String msg){
        super(msg);
    }
}
