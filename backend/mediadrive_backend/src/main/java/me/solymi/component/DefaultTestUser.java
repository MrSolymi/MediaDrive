package me.solymi.component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import me.solymi.model.Role;
import me.solymi.model.User;
import me.solymi.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultTestUser {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;

    @PostConstruct
    public void init() {
        if (userRepo.count() == 0) {
            var user = new User(
                    1L,
                    "testuser",
                    "testuser@example.com",
                    Role.USER
            );
            user.setPassword(encoder.encode("password123"));
            userRepo.save(user);
            System.out.println("Default test user created");
        }
    }
}
