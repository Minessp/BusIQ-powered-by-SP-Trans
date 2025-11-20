package br.com.msp.busiq.config.security;

import br.com.msp.busiq.core.gateway.auth.AuthGateway;
import br.com.msp.busiq.core.gateway.auth.TokenGateway;
import br.com.msp.busiq.core.usecases.auth.*;
import br.com.msp.busiq.infrastructure.gateway.auth.AuthenticateGatewayImpl;
import br.com.msp.busiq.infrastructure.gateway.auth.jwt.JwtAuthenticationProvider;
import br.com.msp.busiq.infrastructure.gateway.auth.jwt.JwtTokenGateway;
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
    ExtractTokenSubjectCase validateTokenCase(TokenGateway tokenGateway) {
        return new ExtractTokenSubjectInteractor(tokenGateway);
    }

    @Bean
    AuthGateway authGateway(UserRepository userRepository, GenerateTokenCase generateTokenCase,
                            PasswordEncoder passwordEncoder) {
        return new AuthenticateGatewayImpl(userRepository, generateTokenCase, passwordEncoder);
    }

    @Bean
    TokenGateway tokenGateway() {
        return new JwtTokenGateway(secret);
    }

    @Bean
    public JwtFilter jwtFilter(AuthenticationManager authenticationManager) {
        return new JwtFilter(authenticationManager);
    }

    @Bean
    JwtAuthenticationProvider jwtAuthenticationProvider(ExtractTokenSubjectCase extractTokenSubjectCase,
                                                        UserRepository userRepository) {
        return new JwtAuthenticationProvider(extractTokenSubjectCase, userRepository);
    }
}
