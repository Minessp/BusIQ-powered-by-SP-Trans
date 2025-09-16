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
}
