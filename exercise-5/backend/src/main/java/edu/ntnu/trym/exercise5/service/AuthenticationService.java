package edu.ntnu.trym.exercise5.service;

import edu.ntnu.trym.exercise5.dto.UserCreationDTO;
import edu.ntnu.trym.exercise5.model.User;
import edu.ntnu.trym.exercise5.repo.UserRepository;
import edu.ntnu.trym.exercise5.security.AuthenticationRequest;
import edu.ntnu.trym.exercise5.security.AuthenticationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 This service class handles the business logic for authentication-related operations.
 It implements the IAuthenticationService interface.

 */
@RequiredArgsConstructor
@Service
public class AuthenticationService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;
    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    /**
     * Registers a user to the system.
     *
     * @param userCreateDTO the information of the user to be registered.
     * @return an AuthenticationResponse containing the JWT token of the user.
     * @throws IllegalArgumentException if the username of the user already exists in the database.
     */
    @Transactional
    public AuthenticationResponse register(UserCreationDTO userCreateDTO) {
        User user = User
                .builder()
                .username(userCreateDTO.getUsername())
                .password(passwordEncoder.encode(userCreateDTO.getPassword()))
                .email(userCreateDTO.getEmail())
                .build();
        if (userRepository.findUserByUsername(userCreateDTO.getUsername()).isPresent())
            throw new IllegalArgumentException("Username already exists");
        userRepository.save(user);

        logger.info(String.format("User %s has been saved in the DB!", user.getUsername()));

        String jwtToken = jwtService.generateToken(user);
        logger.info("Their JWT is: " + jwtToken);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    /**
     * Authenticates a user with the given credentials.
     *
     * @param request the authentication request containing the username and password of the user.
     * @return an AuthenticationResponse containing the JWT token of the authenticated user.
     * @throws UsernameNotFoundException if the username of the user is not found in the database.
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        logger.info("Authenticating user information for " + request.getUsername());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findUserByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        String jwtToken = jwtService.generateToken(user);
        logger.info("New token " + jwtToken);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}