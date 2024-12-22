package me.solymi.dto;

import java.util.List;

public record AllTokenResponse(
        List<String> tokens
) { }
