package br.com.msp.busiq.core.usecases.user;

import br.com.msp.busiq.core.gateway.user.UserGateway;
import br.com.msp.busiq.infrastructure.dtos.user.CreateUserRequest;
import br.com.msp.busiq.infrastructure.persistence.entities.UserEntity;

public class CreateUserInteractor implements CreateUserCase {
    private final UserGateway userGateway;

    public CreateUserInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public void execute(CreateUserRequest request) {
        userGateway.createUser(request);
    }
}
