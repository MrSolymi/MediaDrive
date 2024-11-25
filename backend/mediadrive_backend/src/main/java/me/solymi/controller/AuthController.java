package me.solymi.controller;

import lombok.RequiredArgsConstructor;
import me.solymi.dto.LoginRequest;
import me.solymi.service.AuthenticationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authService;

    @PostMapping(path = "/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest.username());
        return authService.login(loginRequest);
    }
}
