package com.example.ticketBooking.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {
    private LocalDateTime time;
    private int status;
    private String error;
    private String path;

    public ApiErrorResponse(int status, String error, String path) {
        this.time = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.path = path;
    }
}
