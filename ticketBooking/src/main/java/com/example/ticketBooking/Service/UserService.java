package com.example.ticketBooking.Service;

import com.example.ticketBooking.dto.LoginResponse;
import com.example.ticketBooking.dto.UserDTO;
import com.example.ticketBooking.dto.loginRequest;
import com.example.ticketBooking.dto.userResponse;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {
    userResponse createUser(UserDTO userDTO);
    LoginResponse loginUser(loginRequest loginRequest);
    List<userResponse> getAllUsers();
}
