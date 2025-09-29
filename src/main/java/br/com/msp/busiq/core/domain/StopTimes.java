package br.com.msp.busiq.core.domain;

import java.time.LocalTime;

/**
 * Representa o horário de parada de uma viagem do GTFS.
 * @param tripId Identificador da viagem.
 * @param arrivalTime Horário de chegada na parada (formato HH:mm:ss).
 * @param departureTime Horário de saída da parada (formato HH:mm:ss).
 * @param stopId Identificador da parada.
 * @param stopSequence Ordem da parada na viagem.
 */
public record StopTimes(String tripId,
                        LocalTime arrivalTime,
                        LocalTime departureTime,
                        String stopId,
                        int stopSequence
) {
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

        public StopTimes build() {
            return new StopTimes(tripId,
                                 arrivalTime,
                                 departureTime,
                                 stopId,
                                 stopSequence);
        }
    }
}
