package br.com.msp.busiq.core.gateway.auth;

import br.com.msp.busiq.infrastructure.persistence.entities.UserEntity;

public interface TokenGateway {
    String generateToken(UserEntity user);
    String validateToken(String token);
}
