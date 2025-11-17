package br.com.msp.busiq.core.usecases.user;

import br.com.msp.busiq.infrastructure.dtos.user.CreateUserRequest;
import br.com.msp.busiq.infrastructure.persistence.entities.UserEntity;

public interface CreateUserCase {
    UserEntity execute(CreateUserRequest createUserRequest);
}
