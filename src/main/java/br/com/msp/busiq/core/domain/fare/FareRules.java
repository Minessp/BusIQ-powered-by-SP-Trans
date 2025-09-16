package br.com.msp.busiq.core.domain.fare;

/**
 * Representa uma regra de tarifa do GTFS.
 * @param fareId Identificador da tarifa.
 * @param routeId Identificador da rota.
 * @param originId Identificador da origem (pode ser nulo).
 * @param destinationId Identificador do destino (pode ser nulo).
 * @param containsId Identificador de área contida (pode ser nulo).
 */
public record FareRules(String fareId,
                        String routeId,
                        String originId,
                        String destinationId,
                        String containsId
) {
}

