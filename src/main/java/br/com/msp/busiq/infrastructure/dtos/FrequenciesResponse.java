package br.com.msp.busiq.infrastructure.dtos;

import java.time.LocalTime;

public record FrequenciesResponse(String tripId,
                                   LocalTime startTime,
                                   LocalTime endTime,
                                   int headwaySecs) {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String tripId;
        private LocalTime startTime;
        private LocalTime endTime;
        private int headwaySecs;

        public Builder builder() {
            return this;
        }

        public Builder tripId(String tripId) {
            this.tripId = tripId;
            return this;
        }

        public Builder startTime(LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder endTime(LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder headwaySecs(int headwaySecs) {
            this.headwaySecs = headwaySecs;
            return this;
        }

        public FrequenciesResponse build() {
            return new FrequenciesResponse(tripId,
                                           startTime,
                                           endTime,
                                           headwaySecs);
        }
    }
}
