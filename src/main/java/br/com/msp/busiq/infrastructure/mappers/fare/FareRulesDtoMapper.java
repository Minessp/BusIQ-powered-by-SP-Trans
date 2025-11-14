package br.com.msp.busiq.infrastructure.mappers.fare;

import br.com.msp.busiq.core.domain.fare.FareRules;
import br.com.msp.busiq.infrastructure.dtos.fare.FareRulesResponse;
import br.com.msp.busiq.infrastructure.persistence.entities.fare.FareRulesEntity;

public class FareRulesDtoMapper {
    public FareRules toDomain(FareRulesEntity fareRulesEntity) {
        return FareRules.builder()
                .fareId(fareRulesEntity.getFareId())
                .routeId(fareRulesEntity.getRouteId())
                .originId(fareRulesEntity.getOriginId())
                .destinationId(fareRulesEntity.getDestinationId())
                .containsId(fareRulesEntity.getContainsId())
                .build();
    }

    public FareRulesResponse toResponse(FareRules fareRules) {
        return FareRulesResponse.builder()
                .fareId(fareRules.fareId())
                .routeId(fareRules.routeId())
                .originId(fareRules.originId())
                .destinationId(fareRules.destinationId())
                .containsId(fareRules.containsId())
                .build();
    }

    public FareRulesEntity toEntity(FareRules fareRules) {
        return new FareRulesEntity(fareRules.fareId(),
                                   fareRules.routeId(),
                                   fareRules.originId(),
                                   fareRules.destinationId(),
                                   fareRules.containsId());
    }
}
