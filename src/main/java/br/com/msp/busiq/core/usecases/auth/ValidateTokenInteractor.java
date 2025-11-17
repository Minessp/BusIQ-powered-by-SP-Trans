package br.com.msp.busiq.core.usecases.auth;

import br.com.msp.busiq.core.gateway.auth.TokenGateway;

public class ValidateTokenInteractor implements ValidateTokenCase {
    private final TokenGateway tokenGateway;

    public ValidateTokenInteractor(TokenGateway tokenGateway) {
        this.tokenGateway = tokenGateway;
    }

    @Override
    public String execute(String token) {
        return tokenGateway.validateToken(token);
    }
}
