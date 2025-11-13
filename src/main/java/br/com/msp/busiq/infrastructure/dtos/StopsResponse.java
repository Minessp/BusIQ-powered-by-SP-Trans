package br.com.msp.busiq.infrastructure.dtos;

public record StopsResponse(String stopId,
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

        public StopsResponse build() {
            return new StopsResponse(stopId,
                                     stopName,
                                     stopDesc,
                                     stopLat,
                                     stopLon);
        }
    }
}
