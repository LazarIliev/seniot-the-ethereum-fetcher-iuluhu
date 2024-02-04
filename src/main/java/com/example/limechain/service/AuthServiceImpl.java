package com.example.limechain.service;

import com.example.limechain.dto.LoginRequest;
import com.example.limechain.dto.LoginResponse;
import com.example.limechain.service.api.AuthService;
import com.example.limechain.service.api.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public LoginResponse authRequest(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

        return getToken(userDetails);
    }

    private LoginResponse getToken(UserDetails userDetails) {
        String jwtToken = jwtService.generateToken(userDetails);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return loginResponse;
    }
}
