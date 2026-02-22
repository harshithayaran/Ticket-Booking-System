package com.example.ticketBooking.repository;

import com.example.ticketBooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
