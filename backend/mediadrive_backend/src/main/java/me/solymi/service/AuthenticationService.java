package me.solymi.service;

import me.solymi.dto.LoginRequest;

public interface AuthenticationService {

    String login(LoginRequest loginRequest);
}
