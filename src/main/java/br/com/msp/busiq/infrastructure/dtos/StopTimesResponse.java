package br.com.msp.busiq.infrastructure.dtos;

import java.time.LocalTime;

public record StopTimesResponse(String tripId,
                                 LocalTime arrivalTime,
                                 LocalTime departureTime,
                                 String stopId,
                                 int stopSequence) {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String tripId;
        private LocalTime arrivalTime;
        private LocalTime departureTime;
        private String stopId;
        private int stopSequence;

        public Builder builder() {
            return this;
        }

        public Builder tripId(String tripId) {
            this.tripId = tripId;
            return this;
        }

        public Builder arrivalTime(LocalTime arrivalTime) {
            this.arrivalTime = arrivalTime;
            return this;
        }

        public Builder departureTime(LocalTime departureTime) {
            this.departureTime = departureTime;
            return this;
        }

        public Builder stopId(String stopId) {
            this.stopId = stopId;
            return this;
        }

        public Builder stopSequence(int stopSequence) {
            this.stopSequence = stopSequence;
            return this;
        }

        public StopTimesResponse build() {
            return new StopTimesResponse(tripId,
                                         arrivalTime,
                                         departureTime,
                                         stopId,
                                         stopSequence);
        }
    }
}
