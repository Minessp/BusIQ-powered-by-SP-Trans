package br.com.msp.busiq.core.domain;

import java.time.LocalTime;

public record StopTimes(String tripId,
                        LocalTime arrivalTime,
                        LocalTime departureTime,
                        String stopId,
                        int stopSequence
) {
}
