package me.solymi.component;

import lombok.extern.slf4j.Slf4j;
import me.solymi.model.User;
import me.solymi.security.ApiAuthenticationToken;
import me.solymi.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApiAuthenticationProvider implements AuthenticationProvider {
    private final JwtService jwtService;

    @Autowired
    public ApiAuthenticationProvider(final JwtService jwtService) {
        this.jwtService = jwtService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final ApiAuthenticationToken token = (ApiAuthenticationToken) authentication;

        final User user = this.jwtService.extractUser(token.getCredentials());

        return new ApiAuthenticationToken(user, token.getCredentials());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(ApiAuthenticationToken.class);
    }
}
