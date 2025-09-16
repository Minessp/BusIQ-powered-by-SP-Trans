package br.com.msp.busiq.core.domain;

/**
 * Representa um ponto de um shape (trajeto) do GTFS.
 * @param shapeId Identificador do shape.
 * @param lat Latitude do ponto.
 * @param lon Longitude do ponto.
 * @param sequence Ordem do ponto no shape.
 * @param distTraveled Distância percorrida desde o início do shape.
 */
public record Shapes(String shapeId,
                     double lat,
                     double lon,
                     int sequence,
                     double distTraveled) {
}

