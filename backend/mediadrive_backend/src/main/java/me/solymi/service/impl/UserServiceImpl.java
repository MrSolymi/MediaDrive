package me.solymi.service.impl;

import lombok.RequiredArgsConstructor;
import me.solymi.dto.LoginRequest;
import me.solymi.dto.LoginResponse;
import me.solymi.dto.RegisterRequest;
import me.solymi.dto.RegisterResponse;
import me.solymi.exception.ApiException;
import me.solymi.model.AuditAction;
import me.solymi.model.Role;
import me.solymi.model.User;
import me.solymi.repository.UserRepo;
import me.solymi.service.AuditService;
import me.solymi.service.InviteService;
import me.solymi.service.JwtService;
import me.solymi.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final AuditService auditService;
    private final JwtService jwtService;
    private final InviteService inviteService;
    private final PasswordEncoder passwordEncoder;

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

    @Override
    public RegisterResponse register(RegisterRequest request, String ip) {
        requestCheck(request);

        if (userRepo.findByUsername(request.username()) != null)
            throw ApiException.registerError("Username is already existing!", 0);

        if (userRepo.findByEmail(request.email()) != null)
            throw ApiException.registerError("Email is already used!", 1);

        if (!request.password().equals(request.passwordConfirm()))
            throw ApiException.registerError("passwords are not matching!", 3);

        var token = inviteService.getToken(request.inviteToken());

        if (token == null)
            throw ApiException.registerError("Invalid invite token!", 4);

        if (!inviteService.isValid(token))
            throw ApiException.registerError("Token is expired or already used!", 4);

        var user = new User(request.username(), request.email(), passwordEncoder.encode(request.password()), Role.USER);
        userRepo.save(user);
        inviteService.useToken(token);
        inviteService.saveToken(token);
        auditService.audit(user, AuditAction.REGISTER, "Registered from " + ip);

        return new RegisterResponse("succeeded");
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    private void requestCheck(RegisterRequest request) {
        if (request.username().isBlank()) throw ApiException.registerError("Username is missing!", 0);
        if (request.email().isBlank()) throw ApiException.registerError("Email is missing!", 1);
        if (request.password().isBlank()) throw ApiException.registerError("Password is missing!", 2);
        if (request.passwordConfirm().isBlank()) throw ApiException.registerError("Password confirmation is missing!", 3);
        if (request.inviteToken().isBlank()) throw ApiException.registerError("Invite token is missing!", 4);

    }
}
