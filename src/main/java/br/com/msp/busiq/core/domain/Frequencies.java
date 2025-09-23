package br.com.msp.busiq.core.domain;

import java.time.LocalTime;

/**
 * Representa a frequência de viagens para um determinado trip_id no GTFS.
 * @param tripId Identificador da viagem.
 * @param startTime Hora de início da frequência (formato HH:mm:ss).
 * @param endTime Hora de término da frequência (formato HH:mm:ss).
 * @param headwaySecs Intervalo entre viagens, em segundos.
 */
public record Frequencies(String tripId,
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

        public Frequencies build() {
            return new Frequencies(tripId, startTime, endTime, headwaySecs);
        }
    }
}
