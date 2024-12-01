package me.solymi.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import me.solymi.exception.ApiException;
import me.solymi.model.Role;
import me.solymi.model.User;
import me.solymi.service.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final JWTVerifier jwtVerifier;
    private final Algorithm jwtAlgorithm;

    @Override
    public DecodedJWT verify(String token) {
        //if (isTokenExpired(jwtVerifier.verify(token))) throw ApiException.unauthorized("Token expired");
        return jwtVerifier.verify(token);
    }

    @Override
    public User extractUser(DecodedJWT jwt) {
        var username = jwt.getSubject();
        var idString = jwt.getClaim("id").asString();
        var email = jwt.getClaim("email").asString();
        var role = jwt.getClaim("role").asString();
        if (username != null && idString != null && email != null && role != null)
            return new User(Long.parseLong(idString), username, email, Role.valueOf(role.toUpperCase()));
        throw new BadCredentialsException("Malformed token");
    }

    @Override
    public String generateJwt(User user) {
        return JWT.create()
                .withIssuer("solymi.tech")
                .withSubject(user.getUsername())
                .withExpiresAt(Instant.now().plus(1000, TimeUnit.DAYS.toChronoUnit()))
                .withClaim("id", user.getId().toString())
                .withClaim("email", user.getEmail())
                .withClaim("role", user.getRole().name())
                .sign(jwtAlgorithm);
    }
}
