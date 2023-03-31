package edu.ntnu.trym.exercise5.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the response sent by the server after a successful authentication.
 * It contains a single field `token`, which is a string representing the JWT (JSON Web Token) used for authentication.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    //TODO: Maybe move to dto
    private String token;
}