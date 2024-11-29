package me.solymi.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.solymi.security.ApiAuthenticationToken;
import me.solymi.service.JwtService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiAuthenticationProvider implements AuthenticationProvider {
    private final JwtService jwtService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var token = (ApiAuthenticationToken) authentication;

        var user = jwtService.extractUser(token.getCredentials());

        return new ApiAuthenticationToken(user, token.getCredentials());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(ApiAuthenticationToken.class);
    }
}
