package br.com.msp.busiq.infrastructure.dtos;

public record TripsResponse(String routeId,
                             String serviceId,
                             String tripId,
                             String tripHeadsign,
                             int directionId,
                             String shapeId) {

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

        public TripsResponse build() {
            return new TripsResponse(routeId,
                                     serviceId,
                                     tripId,
                                     tripHeadsign,
                                     directionId,
                                     shapeId);
        }
    }
}
