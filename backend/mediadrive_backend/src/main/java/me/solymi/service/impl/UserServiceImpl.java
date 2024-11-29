package me.solymi.service.impl;

import lombok.RequiredArgsConstructor;
import me.solymi.dto.LoginRequest;
import me.solymi.dto.LoginResponse;
import me.solymi.exception.ApiException;
import me.solymi.model.AuditAction;
import me.solymi.repository.UserRepo;
import me.solymi.service.AuditService;
import me.solymi.service.JwtService;
import me.solymi.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final AuditService auditService;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request, String ip) {
        if (request.username().isBlank()) throw ApiException.badRequest("Username is missing!");
        if (request.password().isBlank()) throw ApiException.badRequest("Password is missing!");

        var user = userRepo.findByUsername(request.username());

        if (user == null)
            throw ApiException.badRequest("Invalid credentials");

        if (!encoder.matches(request.password(), user.getPassword()))
            throw ApiException.badRequest("Invalid credentials");

        auditService.audit(user, AuditAction.LOGIN, "Logged in from " + ip);
        user.setLastLogin(ZonedDateTime.now());
        user.setLastIp(ip);
        userRepo.save(user);

        return new LoginResponse(jwtService.generateJwt(user));
    }
}
