package br.com.msp.busiq.core.usecases.user;

import br.com.msp.busiq.core.gateway.user.UserGateway;

public class DeleteUserByIdInteractor implements DeleteUserByIdCase {
    private final UserGateway userGateway;

    public DeleteUserByIdInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public void execute(String id) {
        userGateway.deleteUserById(id);
    }
}
