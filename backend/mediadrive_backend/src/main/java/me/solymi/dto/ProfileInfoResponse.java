package me.solymi.dto;

import me.solymi.model.Role;

public record ProfileInfoResponse(
        String username,
        String email,
        Role role
) {
}
