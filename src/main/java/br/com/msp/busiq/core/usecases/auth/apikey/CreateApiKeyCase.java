package br.com.msp.busiq.core.usecases.auth.apikey;

import br.com.msp.busiq.core.domain.UserPrincipal;
import br.com.msp.busiq.infrastructure.dtos.auth.CreateApiKeyResponse;

public interface CreateApiKeyCase {
    CreateApiKeyResponse execute(UserPrincipal userPrincipal);
}
