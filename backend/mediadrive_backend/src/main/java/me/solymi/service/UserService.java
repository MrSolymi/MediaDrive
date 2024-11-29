package me.solymi.service;

import me.solymi.dto.LoginRequest;
import me.solymi.dto.LoginResponse;

public interface UserService {
    LoginResponse login(LoginRequest request, String ip);
}
