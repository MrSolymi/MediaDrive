package me.solymi.service.impl;

import lombok.RequiredArgsConstructor;
import me.solymi.repository.UserRepo;
import me.solymi.service.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public UserDetailsService getUserDetailsService() {
        return username -> userRepo.findByUsername(username);
    }
}
