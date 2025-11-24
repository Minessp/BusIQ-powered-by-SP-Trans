package br.com.msp.busiq.infrastructure.dtos.user;

import java.util.Set;

public record GetUserResponse(String id,
                               String email,
                               Set<String> roles) {
}
