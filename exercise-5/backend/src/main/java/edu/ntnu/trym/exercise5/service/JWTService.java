package edu.ntnu.trym.exercise5.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 A service class responsible for generating and validating JWT tokens for authentication.
 */
@Service
public class JWTService {

    private static final String SECRET_KEY = "6A586E3272357538782F413F4428472B4B6250645367566B5970337336763979";
    private final Logger logger = LoggerFactory.getLogger(JWTService.class);

    /**
     * Extracts the username from a JWT token.
     *
     * @param token The JWT token to extract the username from
     * @return The username associated with the token
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts a claim from a JWT token.
     *
     * @param token         The JWT token to extract the claim from
     * @param claimsResolver A function that takes a Claims object and returns a T object representing the desired claim
     * @param <T>           The type of the desired claim
     * @return The claim represented by a T object
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Checks if a JWT token is valid for a given user.
     *
     * @param token        The JWT token to validate
     * @param userDetails The UserDetails object representing the user to validate the token for
     * @return true if the token is valid for the user, false otherwise
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Checks if a JWT token has expired.
     *
     * @param token The JWT token to check
     * @return true if the token has expired, false otherwise
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration date from a JWT token.
     *
     * @param token The JWT token to extract the expiration date from
     * @return The expiration date associated with the token
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Generates a JWT token for a given user.
     *
     * @param userDetails The UserDetails object representing the user to generate the token for
     * @return The generated JWT token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> extraClaims = new HashMap<>();
        return generateToken(extraClaims, userDetails);
    }

    /**
     * Generates a JWT token with extra claims for a given user.
     *
     * @param extraClaims  A Map containing extra claims to include in the token
     * @param userDetails The UserDetails object representing the user to generate the token for
     * @return The generated JWT token
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extracts all the claims from the given JWT token.
     *
     * @param token The JWT token from which to extract the claims.
     * @return A {@link Claims} object containing all the claims from the token.
     * @throws Exception If there is an error parsing the token.
     */
    private Claims extractAllClaims(String token) {
        logger.info("Token being checked: " + token);
        logger.info("Token length: " + token.length());
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            logger.error("Error parsing JWT token: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Gets the signing key used to sign JWT tokens.
     *
     * @return A {@link Key} object representing the signing key.
     */
    private Key getSigningKey() {
        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }
}