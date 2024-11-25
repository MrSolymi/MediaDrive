package me.solymi.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER(List.of(new SimpleGrantedAuthority("ROLE_USER")));

    private final List<GrantedAuthority> authorities;
}
