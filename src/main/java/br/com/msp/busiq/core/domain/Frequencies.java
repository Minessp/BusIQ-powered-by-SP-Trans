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
}
