package me.solymi.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import me.solymi.model.User;

public interface JwtService {

    DecodedJWT verify(String token);

    User extractUser(DecodedJWT jwt);

    String generateJwt(User user);
}
