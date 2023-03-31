package edu.ntnu.trym.exercise5.controller;

import edu.ntnu.trym.exercise5.dto.UserCreationDTO;
import edu.ntnu.trym.exercise5.dto.UserDTO;
import edu.ntnu.trym.exercise5.mapper.UserMapper;
import edu.ntnu.trym.exercise5.model.User;
import edu.ntnu.trym.exercise5.repo.UserRepository;
import edu.ntnu.trym.exercise5.security.AuthenticationRequest;
import edu.ntnu.trym.exercise5.security.AuthenticationResponse;
import edu.ntnu.trym.exercise5.service.AuthenticationService;
import edu.ntnu.trym.exercise5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final AuthenticationService authService;
    private final UserMapper userMapper = new UserMapper();
    private final Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/sign-up")
    public ResponseEntity<AuthenticationResponse> createUser(@RequestBody UserCreationDTO userDTO) {
        logger.info("User " + userDTO + " has been registered!");
        //TODO: if user already exists, return response entity stating that to frontend.
        try {
            AuthenticationResponse authResponse = authService.register(userDTO);
            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            logger.warn("Internal error has occurred: " + e.getMessage());
            e.getStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> getUser(@RequestBody UserDTO userDTO) {
        logger.info("User " + userDTO.getUsername() + " has attempted to log in!");
        try {
            AuthenticationResponse authResponse = authService.authenticate(AuthenticationRequest
                    .builder()
                    .username(userDTO.getUsername())
                    .password(userDTO.getPassword())
                    .build());
            logger.info("User " + userDTO.getUsername() + " has logged in!");
            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            logger.warn("Internal error has occurred: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

}
