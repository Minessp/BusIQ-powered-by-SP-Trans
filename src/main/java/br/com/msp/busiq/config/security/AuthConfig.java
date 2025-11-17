package br.com.msp.busiq.config.security;

import br.com.msp.busiq.core.gateway.auth.AuthGateway;
import br.com.msp.busiq.core.gateway.auth.TokenGateway;
import br.com.msp.busiq.core.usecases.auth.*;
import br.com.msp.busiq.infrastructure.gateway.auth.AuthenticateGatewayImpl;
import br.com.msp.busiq.infrastructure.gateway.auth.JwtTokenGateway;
import br.com.msp.busiq.infrastructure.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthConfig {
    @Value("${jwt.secret}")
    private String secret;

    @Bean
    AuthenticateUserCase authenticateUserCase(AuthGateway authGateway) {
        return new AuthenticateUserInteractor(authGateway);
    }

    @Bean
    GenerateTokenCase generateTokenCase(TokenGateway tokenGateway) {
        return new GenerateTokenInteractor(tokenGateway);
    }

    @Bean
    ValidateTokenCase validateTokenCase(TokenGateway tokenGateway) {
        return new ValidateTokenInteractor(tokenGateway);
    }

    @Bean
    AuthGateway authGateway(UserRepository userRepository, AuthenticationManager authenticationManager,
                            GenerateTokenCase generateTokenCase, PasswordEncoder passwordEncoder) {
        return new AuthenticateGatewayImpl(userRepository, authenticationManager, generateTokenCase, passwordEncoder);
    }

    @Bean
    TokenGateway tokenGateway() {
        return new JwtTokenGateway(secret);
    }

    @Bean
    public SecurityFilter securityFilter(ValidateTokenCase validateTokenCase, UserRepository userRepository) {
        return new SecurityFilter(validateTokenCase, userRepository);
    }
}
