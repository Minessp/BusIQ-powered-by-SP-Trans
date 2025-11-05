package br.com.msp.busiq.infrastructure.exceptions;

import java.util.List;

public record ApiError(String timestamp,
                       int code,
                       List<String> errors) {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String timestamp;
        private int code;
        private List<String> errors;

        public Builder builder() {
            return this;
        }

        public Builder timestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public Builder errors(List<String> errors) {
            this.errors = errors;
            return this;
        }

        public ApiError build() {
            return new ApiError(timestamp,
                                code,
                                errors);
        }
    }
}
