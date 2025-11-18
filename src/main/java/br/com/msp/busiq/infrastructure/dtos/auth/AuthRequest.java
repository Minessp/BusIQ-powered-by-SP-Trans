package br.com.msp.busiq.infrastructure.dtos.auth;

public record AuthRequest(String email, String password) {
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String email;
        private String password;

        public Builder builder() {
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public AuthRequest build() {
            return new AuthRequest(email, password);
        }
    }
}
