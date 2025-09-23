package br.com.msp.busiq.core.domain;

/**
 * Representa um ponto de um shape (trajeto) do GTFS.
 * @param shapeId Identificador do shape.
 * @param lat Latitude do ponto.
 * @param lon Longitude do ponto.
 * @param sequence Ordem do ponto no shape.
 * @param distTraveled Distância percorrida desde o início do shape.
 */
public record Shapes(String shapeId,
                     double lat,
                     double lon,
                     int sequence,
                     double distTraveled) {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String shapeId;
        private double lat;
        private double lon;
        private int sequence;
        private double distTraveled;

        public Builder builder() {
            return this;
        }

        public Builder shapeId(String shapeId) {
            this.shapeId = shapeId;
            return this;
        }

        public Builder lat(double lat) {
            this.lat = lat;
            return this;
        }

        public Builder lon(double lon) {
            this.lon = lon;
            return this;
        }

        public Builder sequence(int sequence) {
            this.sequence = sequence;
            return this;
        }

        public Builder distTraveled(double distTraveled) {
            this.distTraveled = distTraveled;
            return this;
        }

        public Shapes build() {
            return new Shapes(shapeId, lat, lon, sequence, distTraveled);
        }
    }
}

