package br.com.msp.busiq.infrastructure.dtos.fare;

import java.math.BigDecimal;

public record FareAttributesResponse(String fareId,
                                     BigDecimal price,
                                     String currencyType,
                                     int paymentMethod,
                                     Integer transfers,
                                     Integer transferDuration
) {
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String fareId;
        private BigDecimal price;
        private String currencyType;
        private int paymentMethod;
        private Integer transfers;
        private Integer transferDuration;

        public Builder builder() {
            return this;
        }

        public Builder fareId(String fareId) {
            this.fareId = fareId;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder currencyType(String currencyType) {
            this.currencyType = currencyType;
            return this;
        }

        public Builder paymentMethod(int paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder transfers(Integer transfers) {
            this.transfers = transfers;
            return this;
        }

        public Builder transferDuration(Integer transferDuration) {
            this.transferDuration = transferDuration;
            return this;
        }

        public FareAttributesResponse build() {
            return new FareAttributesResponse(fareId,
                                              price,
                                              currencyType,
                                              paymentMethod,
                                              transfers,
                                              transferDuration);
        }
    }
}
