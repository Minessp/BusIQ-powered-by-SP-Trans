package br.com.msp.busiq.integrationtests;

import br.com.msp.busiq.infrastructure.dtos.auth.AuthRequest;
import br.com.msp.busiq.infrastructure.dtos.auth.AuthResponse;
import br.com.msp.busiq.infrastructure.dtos.user.CreateUserRequest;
import br.com.msp.busiq.infrastructure.persistence.entities.DatabaseConnectionTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthIT extends DatabaseConnectionTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createUserWithSuccess() {
        CreateUserRequest request = CreateUserRequest.builder()
                .name("Hellas Verona")
                .email("italyleague@dominio.com")
                .password("footballforlife")
                .role("")
                .build();

        ResponseEntity<Void> response = restTemplate.postForEntity("/users", request, Void.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void shouldReturnBadRequestWhenCreatingUserWithExistingEmail() {
        CreateUserRequest requestOriginal = CreateUserRequest.builder()
                .name("Kanye West")
                .email("ye@yeezy.com")
                .password("Runaway@2025")
                .role("ADMIN")
                .build();

        CreateUserRequest requestDuplicated = CreateUserRequest.builder()
                .name("Kanye West")
                .email("ye@yeezy.com")
                .password("Runaway@2025")
                .role("ADMIN")
                .build();

        ResponseEntity<Void> responseOriginal = restTemplate.postForEntity("/users", requestOriginal, Void.class);
        ResponseEntity<Void> responseDuplicated = restTemplate.postForEntity("/users", requestDuplicated, Void.class);

        assertEquals(HttpStatus.CREATED, responseOriginal.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST, responseDuplicated.getStatusCode());
    }

    @Test
    void shouldReturnJwtTokenWhenUserLogIn() {
        CreateUserRequest request = CreateUserRequest.builder()
                .name("Hamburg SV")
                .email("hsvdeutchland@hsv.com")
                .password("nordderby2025")
                .role("USER")
                .build();

        restTemplate.postForEntity("/users", request, Void.class);

        AuthRequest authRequest = AuthRequest.builder()
                .email("hsvdeutchland@hsv.com")
                .password("nordderby2025")
                .build();

        AuthResponse response = restTemplate.postForObject("/auth", authRequest, AuthResponse.class);

        assertNotNull(response);
        assertInstanceOf(String.class, response.token());
        assertFalse(response.token().isEmpty());
    }

    @Test
    void shouldReturnBadRequestWhenUserProvidesInvalidCredentials() {
        CreateUserRequest request = CreateUserRequest.builder()
                .name("Tierney")
                .email("tierney@scotland.com")
                .password("3x2againstDenmarkHero")
                .role("USER")
                .build();

        restTemplate.postForEntity("/users", request, Void.class);

        AuthRequest authRequest = AuthRequest.builder()
                .email("tierney@scotland.com")
                .password("denmarkWasEasy")
                .build();

        ResponseEntity<AuthResponse> response = restTemplate.postForEntity("/auth", authRequest, AuthResponse.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
