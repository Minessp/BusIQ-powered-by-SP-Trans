package br.com.msp.busiq.core.gateway.auth;

import br.com.msp.busiq.infrastructure.dtos.auth.AuthRequest;
import br.com.msp.busiq.infrastructure.dtos.auth.AuthResponse;

public interface AuthGateway {
    AuthResponse authenticate(AuthRequest request);
}
