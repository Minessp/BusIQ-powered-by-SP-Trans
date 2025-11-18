package br.com.msp.busiq.infrastructure.controllers;

import br.com.msp.busiq.core.usecases.auth.AuthenticateUserCase;
import br.com.msp.busiq.infrastructure.dtos.auth.AuthRequest;
import br.com.msp.busiq.infrastructure.dtos.auth.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticateUserCase authenticateUserCase;

    public AuthController(AuthenticateUserCase authenticateUserCase) {
        this.authenticateUserCase = authenticateUserCase;
    }

    @PostMapping
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest request) {
        return ResponseEntity.status(200).body(authenticateUserCase.execute(request));
    }
}
