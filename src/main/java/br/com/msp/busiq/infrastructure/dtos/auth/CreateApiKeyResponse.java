package br.com.msp.busiq.infrastructure.dtos.auth;

public record CreateApiKeyResponse(String apiKey) {
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String apiKey;

        public Builder builder() {
            return this;
        }

        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public CreateApiKeyResponse build() {
            return new CreateApiKeyResponse(apiKey);
        }
    }
}
