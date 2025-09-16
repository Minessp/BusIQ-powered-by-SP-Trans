package br.com.msp.busiq.core.domain.fare;

public record FareRules(String fareId,
                        String routeId,
                        String originId,
                        String destinationId,
                        String containsId
) {
}

