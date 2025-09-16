package br.com.msp.busiq.core.domain;

public record Trips(String routeId,
                    String serviceId,
                    String tripId,
                    String tripHeadsign,
                    int directionId,
                    String shapeId) {

    public Trips {
        if (directionId != 0 && directionId != 1) {
            throw new IllegalArgumentException("directionId precisa ser 0 ou 1");
        }
    }
}
