package br.com.msp.busiq.infrastructure.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUserRequest(
        String name,
        String email,
        String password,
        @NotBlank(message = "É necessário autenticar sua identidade para atualizar os dados.")
        @NotNull(message = "É necessário autenticar sua identidade para atualizar os dados.")
        String currentPassword
) {
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private String email;
        private String password;
        private String currentPassword;

        public Builder builder() {
            return this;
        }

        public Builder name(String name) {
            this.name = name;
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

        public Builder currentPassword(String currentPassword) {
            this.currentPassword = currentPassword;
            return this;
        }

        public UpdateUserRequest build() {
            return new UpdateUserRequest(name, email, password, currentPassword);
        }
    }
}
