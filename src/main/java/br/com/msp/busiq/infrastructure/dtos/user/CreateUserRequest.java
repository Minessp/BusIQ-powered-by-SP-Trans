package br.com.msp.busiq.infrastructure.dtos.user;

public record CreateUserRequest(String name,
                                String email,
                                String password,
                                String role) {
}
