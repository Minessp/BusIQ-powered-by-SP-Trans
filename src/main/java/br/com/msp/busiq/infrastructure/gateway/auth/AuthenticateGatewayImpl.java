package br.com.msp.busiq.infrastructure.gateway.auth;

import br.com.msp.busiq.core.gateway.auth.AuthGateway;
import br.com.msp.busiq.core.usecases.auth.GenerateTokenCase;
import br.com.msp.busiq.infrastructure.dtos.auth.AuthRequest;
import br.com.msp.busiq.infrastructure.dtos.auth.AuthResponse;
import br.com.msp.busiq.infrastructure.persistence.entities.UserEntity;
import br.com.msp.busiq.infrastructure.persistence.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthenticateGatewayImpl implements AuthGateway {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final GenerateTokenCase generateTokenCase;
    private final PasswordEncoder passwordEncoder;

    public AuthenticateGatewayImpl(UserRepository userRepository, AuthenticationManager authenticationManager,
                                   GenerateTokenCase generateTokenCase, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.generateTokenCase = generateTokenCase;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        if(!userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        UserEntity user = userRepository.getUserEntityByEmail(request.email());

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new IllegalArgumentException("Credenciais inválidas");
        }

        String token = generateTokenCase.execute(user);

        return AuthResponse.builder()
                .token(token)
                .build();
    }
}
