package br.com.msp.busiq.core.usecases.auth;

import br.com.msp.busiq.core.gateway.auth.TokenGateway;

public class ExtractTokenSubjectInteractor implements ExtractTokenSubjectCase {
    private final TokenGateway tokenGateway;

    public ExtractTokenSubjectInteractor(TokenGateway tokenGateway) {
        this.tokenGateway = tokenGateway;
    }

    @Override
    public String execute(String token) {
        return tokenGateway.extractTokenSubject(token);
    }
}
