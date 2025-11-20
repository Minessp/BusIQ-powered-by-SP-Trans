package br.com.msp.busiq.infrastructure.gateway.auth.apikey;

import br.com.msp.busiq.core.domain.UserPrincipal;
import br.com.msp.busiq.core.gateway.auth.apikey.ApiKeyGateway;
import br.com.msp.busiq.infrastructure.dtos.auth.CreateApiKeyResponse;
import br.com.msp.busiq.infrastructure.persistence.entities.ApiKeyEntity;
import br.com.msp.busiq.infrastructure.persistence.repositories.ApiKeyRepository;
import br.com.msp.busiq.infrastructure.persistence.repositories.UserRepository;
import com.github.f4b6a3.ulid.UlidCreator;
import org.springframework.security.authorization.AuthorizationDeniedException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class ApiKeyGatewayImpl implements ApiKeyGateway {
    private final ApiKeyRepository apiKeyRepository;
    private final UserRepository userRepository;

    public ApiKeyGatewayImpl(ApiKeyRepository apiKeyRepository, UserRepository userRepository) {
        this.apiKeyRepository = apiKeyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CreateApiKeyResponse createApiKey(UserPrincipal principal) {
        if(principal == null || principal.id() == null || !userRepository.existsById(principal.id())) {
            throw new AuthorizationDeniedException("Recurso não disponível para usuário não autenticado");
        }

        String publicId = UlidCreator.getUlid().toString();
        String secretHash;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(publicId.getBytes(StandardCharsets.UTF_8));
            secretHash = HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Falha ao gerar hash de API Key", e);
        }

        ApiKeyEntity apiKeyEntity = new ApiKeyEntity();

        apiKeyEntity.setPublicId(publicId);
        apiKeyEntity.setSecretHash(secretHash);
        apiKeyEntity.setUser(userRepository.getUserEntityById(principal.id()));

        apiKeyRepository.save(apiKeyEntity);

        return CreateApiKeyResponse.builder()
                .apiKey(publicId + ":" + secretHash)
                .build();
    }
}
