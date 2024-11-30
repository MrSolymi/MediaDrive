package me.solymi.controller;

import me.solymi.model.User;
import me.solymi.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class AbstractController {

    @Autowired
    private UserRepo userRepo;

    protected User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();

        System.out.println("context: " + username);

        return userRepo.findByUsername(username);
    }
}
