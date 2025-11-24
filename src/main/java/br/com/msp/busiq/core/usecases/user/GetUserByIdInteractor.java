package br.com.msp.busiq.core.usecases.user;

import br.com.msp.busiq.core.gateway.user.UserGateway;
import br.com.msp.busiq.infrastructure.dtos.user.GetUserResponse;

public class GetUserByIdInteractor implements GetUserByIdCase {
    private final UserGateway userGateway;

    public GetUserByIdInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public GetUserResponse execute(String id) {
        return userGateway.getUserById(id);
    }
}
