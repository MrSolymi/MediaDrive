package me.solymi.dto;

import me.solymi.model.User;

import java.util.List;

public record AllUsersResponse(
    List<User> users
) { }
