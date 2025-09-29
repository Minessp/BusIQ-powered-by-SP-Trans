package br.com.msp.busiq.core.domain.fare;

/**
 * Representa uma regra de tarifa do GTFS.
 * @param fareId Identificador da tarifa.
 * @param routeId Identificador da rota.
 * @param originId Identificador da origem (pode ser nulo).
 * @param destinationId Identificador do destino (pode ser nulo).
 * @param containsId Identificador de área contida (pode ser nulo).
 */
public record FareRules(String fareId,
                        String routeId,
                        String originId,
                        String destinationId,
                        String containsId
) {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String fareId;
        private String routeId;
        private String originId;
        private String destinationId;
        private String containsId;

        public Builder builder() {
            return this;
        }

        public Builder fareId(String fareId) {
            this.fareId = fareId;
            return this;
        }

        public Builder routeId(String routeId) {
            this.routeId = routeId;
            return this;
        }

        public Builder originId(String originId) {
            this.originId = originId;
            return this;
        }

        public Builder destinationId(String destinationId) {
            this.destinationId = destinationId;
            return this;
        }

        public Builder containsId(String containsId) {
            this.containsId = containsId;
            return this;
        }

        public FareRules build() {
            return new FareRules(fareId,
                                 routeId,
                                 originId,
                                 destinationId,
                                 containsId);
        }
    }
}

