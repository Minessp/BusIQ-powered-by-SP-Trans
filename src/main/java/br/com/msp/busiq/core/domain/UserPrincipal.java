package br.com.msp.busiq.core.domain;

import java.util.List;

public record UserPrincipal(
        String id,
        String email,
        List<String> roles
) {}
