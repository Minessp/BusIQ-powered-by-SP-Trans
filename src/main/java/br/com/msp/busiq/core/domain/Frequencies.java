package br.com.msp.busiq.core.domain;

import java.time.LocalTime;

public record Frequencies(String tripId,
                          LocalTime startTime,
                          LocalTime endTime,
                          int headwaySecs) {
}
