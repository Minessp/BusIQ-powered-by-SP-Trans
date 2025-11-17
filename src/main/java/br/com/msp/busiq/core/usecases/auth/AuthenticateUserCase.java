package br.com.msp.busiq.core.usecases.auth;

import br.com.msp.busiq.infrastructure.dtos.auth.AuthRequest;
import br.com.msp.busiq.infrastructure.dtos.auth.AuthResponse;

public interface AuthenticateUserCase {
    AuthResponse execute(AuthRequest request);
}
