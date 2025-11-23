package br.com.msp.busiq.core.usecases.user;

import br.com.msp.busiq.core.domain.UserPrincipal;
import br.com.msp.busiq.core.gateway.user.UserGateway;
import br.com.msp.busiq.infrastructure.dtos.user.UpdateUserRequest;

public class UpdateUserInteractor implements UpdateUserCase {
    private final UserGateway userGateway;

    public UpdateUserInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public void execute(UserPrincipal principal, UpdateUserRequest request) {
        userGateway.updateUser(principal, request);
    }
}
