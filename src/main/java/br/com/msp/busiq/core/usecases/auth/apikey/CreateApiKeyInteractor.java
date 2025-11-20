package br.com.msp.busiq.core.usecases.auth.apikey;

import br.com.msp.busiq.core.domain.UserPrincipal;
import br.com.msp.busiq.core.gateway.auth.apikey.ApiKeyGateway;
import br.com.msp.busiq.infrastructure.dtos.auth.CreateApiKeyResponse;

public class CreateApiKeyInteractor implements CreateApiKeyCase {
    private final ApiKeyGateway apiKeyGateway;

    public CreateApiKeyInteractor(ApiKeyGateway apiKeyGateway) {
        this.apiKeyGateway = apiKeyGateway;
    }

    @Override
    public CreateApiKeyResponse execute(UserPrincipal userPrincipal) {
        return apiKeyGateway.createApiKey(userPrincipal);
    }
}
