package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/lms/**").permitAll()  // Allow access to student API without authentication
                                .anyRequest().authenticated()  // Require authentication for other requests
                )
                .csrf(AbstractHttpConfigurer::disable  // Disable CSRF globally for testing purposes
                );

        return http.build();
    }
}
