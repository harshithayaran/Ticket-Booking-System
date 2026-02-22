package com.example.ticketBooking.Service;

import com.example.ticketBooking.Exception.UserNotFoundException;
import com.example.ticketBooking.model.User;
import com.example.ticketBooking.repository.userRepository;
import com.example.ticketBooking.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService
        implements UserDetailsService {

    @Autowired
    private userRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UserNotFoundException{

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        return new CustomUserDetails(user);
    }
}
