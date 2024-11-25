package me.solymi.service.impl;

import lombok.RequiredArgsConstructor;
import me.solymi.dto.LoginRequest;
import me.solymi.dto.LoginResponse;
import me.solymi.model.AuditAction;
import me.solymi.repository.UserRepo;
import me.solymi.service.AuditService;
import me.solymi.service.AuthenticationService;
import me.solymi.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final AuditService auditService;
    private final AuthenticationManager manager;
    private final PasswordEncoder encoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest, String ipAddress) {
        Authentication auth = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );
        System.out.println(auth.getName());
        var user = userRepo.findByUsername(loginRequest.username());

        if (!encoder.matches(loginRequest.password(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        auditService.audit(user, AuditAction.LOGIN, "Logged in from " + ipAddress);
        user.setLastLogin(ZonedDateTime.now());
        user.setLastIp(ipAddress);
        userRepo.save(user);

        return new LoginResponse(jwtService.generateToken(user));
    }
}
