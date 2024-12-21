package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(CsrfConfigurer::disable) // Explicitly disable CSRF
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/users/**").permitAll() // Allow unauthenticated access to /users endpoints
                                .anyRequest().authenticated() // Secure all other endpoints
                );
        return http.build();
    }
}
