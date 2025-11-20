package br.com.msp.busiq.core.gateway.auth.apikey;

import br.com.msp.busiq.core.domain.UserPrincipal;
import br.com.msp.busiq.infrastructure.dtos.auth.CreateApiKeyResponse;

public interface ApiKeyGateway {
    CreateApiKeyResponse createApiKey(UserPrincipal principal);
}
