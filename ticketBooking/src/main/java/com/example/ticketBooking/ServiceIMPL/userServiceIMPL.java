package com.example.ticketBooking.ServiceIMPL;

import com.example.ticketBooking.Exception.UserNotFoundException;
import com.example.ticketBooking.Service.UserService;
import com.example.ticketBooking.dto.LoginResponse;
import com.example.ticketBooking.dto.UserDTO;
import com.example.ticketBooking.dto.loginRequest;
import com.example.ticketBooking.dto.userResponse;
import com.example.ticketBooking.model.User;
import com.example.ticketBooking.repository.userRepository;
import com.example.ticketBooking.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class userServiceIMPL implements UserService{

    userRepository userR;

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //Constructor Injection
    public userServiceIMPL(userRepository userR){
        this.userR = userR;
    }

    @Override
    public userResponse createUser(UserDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.getEmail());
        Optional<User> userEmailCheck = userR.findByEmail(userDTO.getEmail());
        if(userEmailCheck.isEmpty()){
            user.setName(userDTO.getName());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setRole("ROLE_USER");
            User saved = userR.save(user);
            userResponse userResponse = new userResponse();
            userResponse.setEmail(saved.getEmail());
            userResponse.setName(saved.getName());
            userResponse.setId(saved.getId());
            return userResponse;
        }
        else{
            throw new UserNotFoundException("Email already exist");
        }
    }

    @Override
    public LoginResponse loginUser(loginRequest loginRequest) {

        User user = userR.findByEmail(loginRequest.getEmail())
                .orElseThrow(() ->
                        new UserNotFoundException("Invalid email"));

        if (!passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword())) {
            throw new UserNotFoundException("Invalid password");
        }

        // 🔐 generate JWT
        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole()
        );

        return new LoginResponse(
                token,
                user.getEmail(),
                user.getRole()
        );
    }


    @Override
    public List<userResponse> getAllUsers() {
        List<User> users = userR.findAll();
        List<userResponse> allUsers = new ArrayList<>();
        for(User user : users){
            userResponse ur = new userResponse();
            ur.setId(user.getId());
            ur.setName(user.getName());
            ur.setEmail(user.getEmail());
            allUsers.add(ur);
        }
        return allUsers;
    }


}
