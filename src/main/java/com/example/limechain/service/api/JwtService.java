package com.example.limechain.service.api;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(UserDetails userDetails);
    long getExpirationTime();
    String extractUsername(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
}
