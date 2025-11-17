package br.com.msp.busiq.infrastructure.dtos.auth;

public record AuthResponse(String token) {
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String token;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public AuthResponse build() {
            return new AuthResponse(token);
        }
    }
}
