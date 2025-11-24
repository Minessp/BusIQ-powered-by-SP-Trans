package br.com.msp.busiq.core.usecases.user;

import br.com.msp.busiq.core.gateway.user.UserGateway;
import br.com.msp.busiq.infrastructure.dtos.user.GetUserResponse;

import java.util.List;

public class GetUsersInteractor implements GetUsersCase {
    private final UserGateway userGateway;

    public GetUsersInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public List<GetUserResponse> execute() {
        return userGateway.getAllUsers();
    }
}
