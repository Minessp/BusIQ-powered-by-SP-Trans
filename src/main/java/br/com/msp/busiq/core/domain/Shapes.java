package br.com.msp.busiq.core.domain;

public record Shapes(String shapeId,
                     double lat,
                     double lon,
                     int sequence,
                     double distTraveled) {
}

