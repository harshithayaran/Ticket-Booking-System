package com.example.ticketBooking.Exception;

public class SeatAlreadyBookedException extends RuntimeException{

    public SeatAlreadyBookedException(String message){
        super(message);
    }
}
