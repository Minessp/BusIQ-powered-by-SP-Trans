package br.com.msp.busiq.infrastructure.dtos.user;

public record CreateUserRequest(String name,
                                String email,
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
