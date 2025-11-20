package br.com.msp.busiq.infrastructure.gateway.auth.jwt;

import br.com.msp.busiq.core.gateway.auth.TokenGateway;
import br.com.msp.busiq.infrastructure.persistence.entities.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.time.Instant;

public class JwtTokenGateway implements TokenGateway {
    private final String secret;

    public JwtTokenGateway(String secret) {
        this.secret = secret;
    }

    @Override
    public String generateToken(UserEntity user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            return JWT.create()
                    .withIssuer("BusIQ-api")
                    .withSubject((user.getId()))
                    .withExpiresAt(Instant.now().plusSeconds(86400))
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao tentar gerar o token JWT", e);
        }
    }

    @Override
    public String extractTokenSubject(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer("BusIQ-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }
}
