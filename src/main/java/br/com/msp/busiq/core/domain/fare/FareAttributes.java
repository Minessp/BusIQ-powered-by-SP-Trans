package br.com.msp.busiq.core.domain.fare;

import java.math.BigDecimal;

/**
 * Representa os atributos de tarifa do GTFS.
 * @param fareId Identificador da tarifa.
 * @param price Valor da tarifa (BigDecimal para precisão).
 * @param currencyType Código da moeda (ex: "BRL").
 * @param paymentMethod Método de pagamento (0 = embarque, 1 = pré-pago).
 * @param transfers Número de transferências permitidas (pode ser nulo).
 * @param transferDuration Duração máxima da transferência em segundos (pode ser nulo).
 */
public record FareAttributes(String fareId,
                             BigDecimal price,
                             String currencyType,
                             int paymentMethod,
                             int transfers,
                             int transferDuration
) {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String fareId;
        private BigDecimal price;
        private String currencyType;
        private int paymentMethod;
        private int transfers;
        private int transferDuration;

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

        public Builder transfers(int transfers) {
            this.transfers = transfers;
            return this;
        }

        public Builder transferDuration(int transferDuration) {
            this.transferDuration = transferDuration;
            return this;
        }

        public FareAttributes build() {
            return new FareAttributes(fareId, price, currencyType, paymentMethod, transfers, transferDuration);
        }
    }
}