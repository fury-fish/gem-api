package com.gemapi.service;

import com.gemapi.dto.auth.AuthenticationRequest;
import com.gemapi.dto.auth.AuthenticationResponse;
import com.gemapi.dto.auth.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
} 