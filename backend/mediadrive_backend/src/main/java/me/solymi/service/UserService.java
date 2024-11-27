package me.solymi.service;

import me.solymi.dto.LoginRequest;
import me.solymi.dto.LoginResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService getUserDetailsService();
}
