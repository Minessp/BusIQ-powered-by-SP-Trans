package br.com.msp.busiq.config.domain.auth.apikey;

import br.com.msp.busiq.core.gateway.auth.apikey.ApiKeyGateway;
import br.com.msp.busiq.core.usecases.auth.apikey.CreateApiKeyInteractor;
import br.com.msp.busiq.core.usecases.auth.apikey.CreateApiKeyCase;
import br.com.msp.busiq.infrastructure.gateway.auth.apikey.ApiKeyAuthorizationManager;
import br.com.msp.busiq.infrastructure.gateway.auth.apikey.ApiKeyGatewayImpl;
import br.com.msp.busiq.infrastructure.persistence.repositories.ApiKeyRepository;
import br.com.msp.busiq.infrastructure.persistence.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiKeyConfig {

    @Bean
    CreateApiKeyCase createApiKeyCase(ApiKeyGateway apiKeyGateway) {
        return new CreateApiKeyInteractor(apiKeyGateway);
    }

    @Bean
    ApiKeyGateway apiKeyGateway(ApiKeyRepository apiKeyRepository, UserRepository userRepository) {
        return new ApiKeyGatewayImpl(apiKeyRepository, userRepository);
    }

    @Bean
    ApiKeyAuthorizationManager apiKeyAuthorizationManager(ApiKeyRepository apiKeyRepository) {
        return new ApiKeyAuthorizationManager(apiKeyRepository);
    }
}
