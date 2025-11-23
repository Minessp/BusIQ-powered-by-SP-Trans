package br.com.msp.busiq.integrationtests;

import br.com.msp.busiq.infrastructure.dtos.auth.AuthRequest;
import br.com.msp.busiq.infrastructure.dtos.auth.AuthResponse;
import br.com.msp.busiq.infrastructure.dtos.user.CreateUserRequest;
import br.com.msp.busiq.infrastructure.dtos.user.UpdateUserRequest;
import br.com.msp.busiq.infrastructure.persistence.entities.DatabaseConnectionTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIT extends DatabaseConnectionTest {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldCreateUserWithSuccess() {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .name("VIscz")
                .email("vivianougg2@outlook.com")
                .password("Passwvviaan22$")
                .role("USER")
                .build();

        ResponseEntity<Void> createUserResponse = restTemplate.postForEntity("/users", createUserRequest, Void.class);

        assertEquals(HttpStatus.CREATED, createUserResponse.getStatusCode());
    }

    @Test
    void shouldNotCreateUserWithBlankName() {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .name("")
                .email("vitrillit@gmail.com")
                .password("vittsa22$%2")
                .role("USER")
                .build();

        ResponseEntity<Void> createUserResponse = restTemplate.postForEntity("/users", createUserRequest, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, createUserResponse.getStatusCode());
    }

    @Test
    void shouldNotCreateUserWithBlankEmail() {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .name("Klanée")
                .email("")
                .password("osnabruckkk*655")
                .role("USER")
                .build();

        ResponseEntity<Void> createUserResponse = restTemplate.postForEntity("/users", createUserRequest, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, createUserResponse.getStatusCode());
    }

    @Test
    void shouldNotCreateUserWithBlankPassword() {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .name("Juanme")
                .email("juanmeghost@hotmail.com")
                .password("")
                .role("USER")
                .build();

        ResponseEntity<Void> createUserResponse = restTemplate.postForEntity("/users", createUserRequest, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, createUserResponse.getStatusCode());
    }

    @Test
    void shouldNotCreateUserWithEmailPatternInvalid() {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .name("Ozcan")
                .email("gssOzcan@o.com")
                .password("OSZZdaww22$")
                .role("USER")
                .build();

        ResponseEntity<Void> createUserResponse = restTemplate.postForEntity("/users", createUserRequest, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, createUserResponse.getStatusCode());
    }

    @Test
    void shouldNotCreateUserWithPasswordPatterInvalid() {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .name("bartolomeu")
                .email("vidaqueseguiu@mlorr.com")
                .password("dsa211")
                .role("USER")
                .build();

        ResponseEntity<Void> createUserResponse = restTemplate.postForEntity("/users", createUserRequest, Void.class);

        assertEquals(HttpStatus.FORBIDDEN, createUserResponse.getStatusCode());
    }

    @Test
    void shouldUpdateUserNameWithSuccess() {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .name("kawanIsreed")
                .email("molocoton@workout.com")
                .password("Palanoddino%$1754")
                .role("USER")
                .build();

        restTemplate.postForEntity("/users", createUserRequest, Void.class);

        AuthRequest authRequest = AuthRequest.builder()
                .email("molocoton@workout.com")
                .password("Palanoddino%$1754")
                .build();

        AuthResponse authResponse = restTemplate.postForObject("/auth", authRequest, AuthResponse.class);

        UpdateUserRequest updateUserRequest = UpdateUserRequest.builder()
                .name("KawanVV")
                .currentPassword("Palanoddino%$1754")
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authResponse.token());

        HttpEntity<?> entity = new HttpEntity<>(updateUserRequest, headers);

        ResponseEntity<Void> updateResponse = restTemplate.exchange("/users", HttpMethod.PATCH, entity, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, updateResponse.getStatusCode());
    }

    @Test
    void shouldReturnBadRequestWhenTriedUpdateUserNameWithoutCurrentPassword() {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .name("Alfred")
                .email("alfred@pipeup.com")
                .password("AlfredWashed##2025")
                .role("USER")
                .build();

        restTemplate.postForEntity("/users", createUserRequest, Void.class);

        AuthRequest authRequest = AuthRequest.builder()
                .email("alfred@pipeup.com")
                .password("AlfredWashed##2025")
                .build();

        AuthResponse authResponse = restTemplate.postForObject("/auth", authRequest, AuthResponse.class);

        UpdateUserRequest updateUserRequest = UpdateUserRequest.builder()
                .name("AlfredOlui")
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authResponse.token());

        HttpEntity<?> entity = new HttpEntity<>(updateUserRequest, headers);

        ResponseEntity<Void> updateResponse = restTemplate.exchange("/users", HttpMethod.PATCH, entity, Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, updateResponse.getStatusCode());
    }
}
