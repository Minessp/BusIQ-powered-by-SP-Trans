package br.com.msp.busiq.core.usecases.auth;

import br.com.msp.busiq.core.gateway.auth.AuthGateway;
import br.com.msp.busiq.infrastructure.dtos.auth.AuthResponse;
import br.com.msp.busiq.infrastructure.dtos.auth.AuthRequest;

public class AuthenticateUserInteractor implements AuthenticateUserCase {
    private final AuthGateway authGateway;

    public AuthenticateUserInteractor(AuthGateway authGateway) {
        this.authGateway = authGateway;
    }

    @Override
    public AuthResponse execute(AuthRequest request) {
        return authGateway.authenticate(request);
    }
}
