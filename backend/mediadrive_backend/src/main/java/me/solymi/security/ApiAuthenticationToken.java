package me.solymi.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import me.solymi.model.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;

public class ApiAuthenticationToken extends AbstractAuthenticationToken {
    private final DecodedJWT token;
    private final User user;

    public ApiAuthenticationToken(final User user, final DecodedJWT token) {
        super(user.getRole().getAuthorities());
        this.token = token;
        this.user = user;
    }

    public ApiAuthenticationToken(final DecodedJWT token) {
        super(null);
        this.token = token;
        this.user = null;
    }

    @Override
    public DecodedJWT getCredentials() {
        return token;
    }

    @Override
    public User getPrincipal() {
        if (user == null) throw new AuthenticationServiceException("Token is not authenticated");

        return user;
    }

    @Override
    public String getName() {
        if (user == null) throw new AuthenticationServiceException("Token is not authenticated");

        return user.getUsername();
    }
}
