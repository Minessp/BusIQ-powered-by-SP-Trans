package br.com.msp.busiq.infrastructure.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateUserRequest(@NotBlank String name,
                                @NotBlank
                                @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
                                        flags = Pattern.Flag.CASE_INSENSITIVE, message = "Email inválido")
                                String email,
                                @NotBlank(message = "Senha é obrigatória")
                                @Pattern(
                                        regexp = "^(?=.*?\\p{Lu})(?=.*?\\p{Ll})(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
                                        message = "A senha deve ter no mínimo 8 caracteres, 1 letra maiúscula," +
                                                " 1 letra minúscula, 1 número e 1 caractere especial"
                                )
                                String password,
                                String role) {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private String email;
        private String password;
        private String role;

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

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public CreateUserRequest build() {
            return new CreateUserRequest(name, email, password, role);
        }
    }
}
