package br.com.msp.busiq.infrastructure.gateway.auth.apikey;

import br.com.msp.busiq.core.domain.UserPrincipal;
import br.com.msp.busiq.infrastructure.gateway.auth.jwt.JwtAuthenticationToken;
import br.com.msp.busiq.infrastructure.persistence.entities.ApiKeyEntity;
import br.com.msp.busiq.infrastructure.persistence.repositories.ApiKeyRepository;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.authorization.AuthorizationResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Supplier;

public class ApiKeyAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    private final ApiKeyRepository apiKeyRepository;

    public ApiKeyAuthorizationManager(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        return null;
    }

    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        AuthorizationManager.super.verify(authentication, object);
    }

    @Override
    public AuthorizationResult authorize(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        if(!(authentication instanceof JwtAuthenticationToken)) {
            throw new AuthorizationDeniedException("Usuário deve estar autenticado");
        }

        if(object.getRequest().getHeader("X-Api-Key") == null) {
            throw new AuthorizationDeniedException("Necessário informar a chave API");
        }

        String apiKeyRequest = object.getRequest().getHeader("X-Api-Key");
        String publicId = apiKeyRequest.substring(0, apiKeyRequest.indexOf(":"));
        String secretHash = apiKeyRequest.substring(apiKeyRequest.indexOf(":") + 1);

        if(!apiKeyRepository.existsByPublicId(publicId)) {
            throw new AuthorizationDeniedException("Api-Key não encontrada");
        }

        ApiKeyEntity apiKey = apiKeyRepository.findByPublicId(publicId);

        if(apiKey.isRevoked() || apiKey.getExpiresAt().isAfter(LocalDateTime.now())) {
            throw new AuthorizationDeniedException("Api-Key não está mais válida");
        }

        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication.get();
        UserPrincipal principal = (UserPrincipal) jwtAuthenticationToken.getPrincipal();

        if(!Objects.equals(principal.id(), apiKey.getUser().getId())) {
            throw new AuthorizationDeniedException("Acesso negado para o usuário informado na Api-Key");
        }

        if(!MessageDigest.isEqual(secretHash.getBytes(), apiKey.getSecretHash().getBytes())) {
            throw new AuthorizationDeniedException("Acesso negado por Api-Key secret inválida");
        }
        return new AuthorizationDecision(true);
    }
}
