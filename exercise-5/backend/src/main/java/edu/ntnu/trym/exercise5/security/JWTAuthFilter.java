package edu.ntnu.trym.exercise5.security;


import edu.ntnu.trym.exercise5.model.User;
import edu.ntnu.trym.exercise5.service.JWTService;
import edu.ntnu.trym.exercise5.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 This class implements the filter that checks for the validity of a JWT token and sets
 the authentication of the user accordingly. It extends the OncePerRequestFilter class
 to ensure that this filter is only executed once per request.
 */
@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(JWTAuthFilter.class);

    /**
     * Checks the validity of the JWT token from the HTTP header and sets the authentication
     * of the user accordingly.
     *
     * @param request     The HTTP request.
     * @param response    The HTTP response.
     * @param filterChain The filter chain.
     * @throws ServletException If there is a servlet exception.
     * @throws IOException      If there is an I/O exception.
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        final String jwt, username;
        final String authHeader = request.getHeader("Authorization");


        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        logger.info("JWT received through the http filter: " + jwt);

        username = jwtService.extractUsername(jwt);
        logger.info("Username found using token: " + username);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            logger.info("User found with token: " + username);
            User user = this.userService.loadByUsername(username);
            if (jwtService.isTokenValid(jwt, user)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

}