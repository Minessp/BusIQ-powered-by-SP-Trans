package br.com.msp.busiq.core.gateway.user;

import br.com.msp.busiq.infrastructure.dtos.user.CreateUserRequest;
import br.com.msp.busiq.infrastructure.persistence.entities.UserEntity;

public interface UserGateway {
    UserEntity createUser(CreateUserRequest request);
}
