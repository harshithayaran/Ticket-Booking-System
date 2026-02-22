package com.example.ticketBooking.controller;

import com.example.ticketBooking.Service.UserService;
import com.example.ticketBooking.dto.LoginResponse;
import com.example.ticketBooking.dto.UserDTO;
import com.example.ticketBooking.dto.loginRequest;
import com.example.ticketBooking.dto.userResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import jakarta.validation.Valid;

@RestController
public class userController {

    UserService userService;
    public userController(UserService u){
        this.userService = u;
    }

    @RequestMapping("ticket_app/signup")
    public userResponse createUser(@Valid @RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @RequestMapping("ticket_app/login")
    public LoginResponse loginUser(@Valid @RequestBody loginRequest loginRequest){
        return userService.loginUser(loginRequest);
    }

    @RequestMapping("ticket_app/getallusers")
    public List<userResponse> getAllUsers(){
        return userService.getAllUsers();
    }
}
