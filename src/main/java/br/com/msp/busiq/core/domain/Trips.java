package br.com.msp.busiq.core.domain;

/**
 * Representa uma viagem (trip) de uma rota no sistema de transporte público, conforme o padrão GTFS.
 * @param routeId Identificador da rota à qual a viagem pertence.
 * @param serviceId Identificador do serviço (calendário de operação).
 * @param tripId Identificador único da viagem.
 * @param tripHeadsign Destino ou direção exibida ao passageiro.
 * @param directionId Identificador da direção (0 ou 1).
 * @param shapeId Identificador do trajeto geográfico da viagem.
 */
public record Trips(String routeId,
                    String serviceId,
                    String tripId,
                    String tripHeadsign,
                    int directionId,
                    String shapeId) {

    /**
     * @exception IllegalArgumentException se directionId não for 0 ou 1
     */
    public Trips {
        if (directionId != 0 && directionId != 1) {
            throw new IllegalArgumentException("directionId precisa ser 0 ou 1");
        }
    }
}
