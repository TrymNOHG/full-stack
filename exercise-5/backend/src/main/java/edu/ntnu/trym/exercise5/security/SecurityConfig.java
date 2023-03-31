package edu.ntnu.trym.exercise5.security;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 Configuration class for Spring Security. Enables Web Security and provides a SecurityFilterChain bean.
 Uses a JwtAuthenticationFilter for authentication and an AuthenticationProvider for authentication management.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTAuthFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;

    /**
     Configures the security filter chain.

     @param http HttpSecurity object used for configuration
     @return A SecurityFilterChain object
     @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .headers().frameOptions().sameOrigin()
                .and()
                .authorizeHttpRequests(authorize ->
                                authorize.requestMatchers("/home", "/login", "/user/sign-up", "/user/login")
                                        .permitAll()
                                        .requestMatchers("/user/**", "/auth/**", "/admin/**", "/calculator/log/**")
                                        .permitAll().anyRequest().authenticated()
                )
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    /**
     Configures CORS mapping for the application.

     @return A WebMvcConfigurer object
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NotNull CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
            }
        };
    }

}
