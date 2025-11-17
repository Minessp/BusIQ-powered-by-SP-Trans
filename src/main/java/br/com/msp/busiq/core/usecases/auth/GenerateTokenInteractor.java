package br.com.msp.busiq.core.usecases.auth;

import br.com.msp.busiq.core.gateway.auth.TokenGateway;
import br.com.msp.busiq.infrastructure.persistence.entities.UserEntity;

public class GenerateTokenInteractor implements GenerateTokenCase {
    private final TokenGateway tokenGateway;

    public GenerateTokenInteractor(TokenGateway tokenGateway) {
        this.tokenGateway = tokenGateway;
    }

    @Override
    public String execute(UserEntity user) {
        return tokenGateway.generateToken(user);
    }
}
