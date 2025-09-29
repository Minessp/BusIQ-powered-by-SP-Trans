package br.com.msp.busiq.core.domain;

/**
 * Representa uma parada (stop) conforme o padrão GTFS.
 * @param stopId Identificador único da parada.
 * @param stopName Nome da parada.
 * @param stopDesc Descrição da parada (pode ser vazio).
 * @param stopLat Latitude da parada em graus decimais.
 * @param stopLon Longitude da parada em graus decimais.
 */
public record Stops(String stopId,
                    String stopName,
                    String stopDesc,
                    double stopLat,
                    double stopLon) {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String stopId;
        private String stopName;
        private String stopDesc;
        private double stopLat;
        private double stopLon;

        public Builder builder() {
            return this;
        }

        public Builder stopId(String stopId) {
            this.stopId = stopId;
            return this;
        }

        public Builder stopName(String stopName) {
            this.stopName = stopName;
            return this;
        }

        public Builder stopDesc(String stopDesc) {
            this.stopDesc = stopDesc;
            return this;
        }

        public Builder stopLat(double stopLat) {
            this.stopLat = stopLat;
            return this;
        }

        public Builder stopLon(double stopLon) {
            this.stopLon = stopLon;
            return this;
        }

        public Stops build() {
            return new Stops(stopId,
                             stopName,
                             stopDesc,
                             stopLat,
                             stopLon);
        }
    }

}
