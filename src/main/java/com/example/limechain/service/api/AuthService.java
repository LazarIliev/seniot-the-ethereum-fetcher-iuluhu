package com.example.limechain.service.api;

import com.example.limechain.dto.LoginRequest;
import com.example.limechain.dto.LoginResponse;

public interface AuthService {
    LoginResponse authRequest(LoginRequest loginRequest);
}
