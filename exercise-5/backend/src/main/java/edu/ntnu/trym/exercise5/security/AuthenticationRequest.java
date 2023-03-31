package edu.ntnu.trym.exercise5.security;

import lombok.*;

/**
 Represents an authentication request made by a user.
 This class contains the necessary fields to authenticate a user, namely the username and password.
 The username and password are required and must be non-null in order to create an instance of this class.
 This class is used by the authentication controller to receive the user's login information.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    //TODO: Maybe move to dto
    /**
     * The username of the user making the authentication request.
     * This field is required and must be non-null.
     */
    @NonNull
    private String username;

    /**
     * The password of the user making the authentication request.
     * This field is required and must be non-null.
     */
    @NonNull
    private String password;
}