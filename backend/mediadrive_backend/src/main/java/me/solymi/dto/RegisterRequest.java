package me.solymi.dto;

public record RegisterRequest(
    String username,
    String email,
    String password,
    String passwordConfirm,
    String inviteToken
) { }
