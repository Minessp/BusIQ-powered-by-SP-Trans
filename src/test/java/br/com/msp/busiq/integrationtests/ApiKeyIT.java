package br.com.msp.busiq.integrationtests;

import br.com.msp.busiq.infrastructure.dtos.auth.AuthRequest;
import br.com.msp.busiq.infrastructure.dtos.auth.AuthResponse;
import br.com.msp.busiq.infrastructure.dtos.auth.CreateApiKeyResponse;
import br.com.msp.busiq.infrastructure.dtos.user.CreateUserRequest;
import br.com.msp.busiq.infrastructure.persistence.entities.DatabaseConnectionTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiKeyIT extends DatabaseConnectionTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnApiKeyWithSuccess() {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .name("Xavier")
                .email("xavier@oxford.com")
                .password("XavierOnTop@@Fullboxchp7")
                .role("USER")
                .build();

        restTemplate.postForEntity("/users", createUserRequest, Void.class);

        AuthRequest authRequest = AuthRequest.builder()
                .email("xavier@oxford.com")
                .password("XavierOnTop@@Fullboxchp7")
                .build();

        AuthResponse jwtTokenResponse = restTemplate.postForObject("/auth", authRequest, AuthResponse.class);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtTokenResponse.token());

        HttpEntity<?> entity = new HttpEntity<>(null, headers);

        ResponseEntity<CreateApiKeyResponse> apiKeyResponse = restTemplate.exchange(
                "/api-key",
                HttpMethod.POST,
                entity,
                CreateApiKeyResponse.class
        );

        assertNotNull(apiKeyResponse);
        assertEquals(HttpStatus.CREATED, apiKeyResponse.getStatusCode());
    }

    @Test
    void shouldReturnForbiddenStatusWhenUserHasNoJwtTryingToCreateApiKey() {
        HttpEntity<?> entity = new HttpEntity<>(null, null);

        ResponseEntity<CreateApiKeyResponse> apiKeyResponse = restTemplate.exchange(
                "/api-key",
                HttpMethod.POST,
                entity,
                CreateApiKeyResponse.class
        );

        assertEquals(HttpStatus.FORBIDDEN, apiKeyResponse.getStatusCode());
    }
}
