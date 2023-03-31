package edu.ntnu.trym.exercise5.security;

import edu.ntnu.trym.exercise5.repo.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

/**
 This class provides application configuration for Spring Security. It sets up the user details service to
 fetch user details from the user repository, creates an authentication provider that uses the user details service,
 and provides a password encoder to be used for password hashing.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

    /**
     * This method returns a UserDetailsService that retrieves user details from the user repository.
     *
     * @return  a UserDetailsService that retrieves user details from the user repository.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    /**
     * This method returns an AuthenticationProvider that uses the user details service and password encoder to
     * authenticate users.
     *
     * @return  an AuthenticationProvider that uses the user details service and password encoder to authenticate users.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * This method returns an AuthenticationManager bean that can be used to authenticate users.
     *
     * @param config    the AuthenticationConfiguration to use
     * @return          an AuthenticationManager bean that can be used to authenticate users.
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * This method return the application's current Password Encoder, which leverages the bcrypt algorithm.
     * The BCryptPasswordEncoder can take in a strength as a parameter and is recommended to take about 1 second to
     * generate.
     *
     * @return  BCrypt password encoder, given as a PasswordEncoder object.
     */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}