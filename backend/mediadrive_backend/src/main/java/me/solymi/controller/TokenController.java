package me.solymi.controller;

import lombok.RequiredArgsConstructor;
import me.solymi.service.JwtService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TokenController {

    private final JwtService jwtService;
}
