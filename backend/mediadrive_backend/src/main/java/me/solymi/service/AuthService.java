package me.solymi.service;

import me.solymi.dto.LoginRequest;
import me.solymi.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest, String ipAddress);
}
