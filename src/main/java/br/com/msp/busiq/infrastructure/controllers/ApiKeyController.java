package br.com.msp.busiq.infrastructure.controllers;

import br.com.msp.busiq.core.domain.UserPrincipal;
import br.com.msp.busiq.core.usecases.auth.apikey.CreateApiKeyCase;
import br.com.msp.busiq.infrastructure.dtos.auth.CreateApiKeyResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-key")
public class ApiKeyController {
    private final CreateApiKeyCase createApiKeyCase;

    public ApiKeyController(CreateApiKeyCase createApiKeyCase) {
        this.createApiKeyCase = createApiKeyCase;
    }

    @PostMapping
    public ResponseEntity<CreateApiKeyResponse> createApiKey(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.status(201).body(createApiKeyCase.execute(userPrincipal));
    }
}
