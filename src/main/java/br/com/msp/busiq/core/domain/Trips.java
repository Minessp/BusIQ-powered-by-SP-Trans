package br.com.msp.busiq.core.domain;

/**
 * Representa uma viagem (trip) de uma rota no sistema de transporte público, conforme o padrão GTFS.
 * @param routeId Identificador da rota à qual a viagem pertence.
 * @param serviceId Identificador do serviço (calendário de operação).
 * @param tripId Identificador único da viagem.
 * @param tripHeadsign Destino ou direção exibida ao passageiro.
 * @param directionId Identificador da direção (0 ou 1).
 * @param shapeId Identificador do trajeto geográfico da viagem.
 */
public record Trips(String routeId,
                    String serviceId,
                    String tripId,
                    String tripHeadsign,
                    int directionId,
                    String shapeId) {

    /**
     * @exception IllegalArgumentException se directionId não for 0 ou 1
     */
    public Trips {
        if (directionId != 0 && directionId != 1) {
            throw new IllegalArgumentException("directionId precisa ser 0 ou 1");
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String routeId;
        private String serviceId;
        private String tripId;
        private String tripHeadsign;
        private int directionId;
        private String shapeId;

        public Builder builder() {
            return this;
        }

        public Builder routeId(String routeId) {
            this.routeId = routeId;
            return this;
        }

        public Builder serviceId(String serviceId) {
            this.serviceId = serviceId;
            return this;
        }

        public Builder tripId(String tripId) {
            this.tripId = tripId;
            return this;
        }

        public Builder tripHeadsign(String tripHeadsign) {
            this.tripHeadsign = tripHeadsign;
            return this;
        }

        public Builder directionId(int directionId) {
            this.directionId = directionId;
            return this;
        }

        public Builder shapeId(String shapeId) {
            this.shapeId = shapeId;
            return this;
        }

        public Trips build() {
            return new Trips(routeId,
                             serviceId,
                             tripId,
                             tripHeadsign,
                             directionId,
                             shapeId);
        }
    }
}
