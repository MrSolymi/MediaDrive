package me.solymi.service;

import me.solymi.dto.LoginRequest;
import me.solymi.dto.LoginResponse;
import me.solymi.dto.RegisterRequest;
import me.solymi.dto.RegisterResponse;

public interface UserService {
    LoginResponse login(LoginRequest request, String ip);
    RegisterResponse register(RegisterRequest request, String ip);
}
