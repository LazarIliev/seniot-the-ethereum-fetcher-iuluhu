package com.example.limechain.controller;

import com.example.limechain.dto.LoginResponse;
import com.example.limechain.dto.LoginRequest;
import com.example.limechain.service.api.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/lime/authenticate")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.authRequest(loginRequest));
    }
}
