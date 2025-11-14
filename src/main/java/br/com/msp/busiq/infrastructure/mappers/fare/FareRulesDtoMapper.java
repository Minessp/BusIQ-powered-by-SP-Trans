package br.com.msp.busiq.infrastructure.mappers.fare;

import br.com.msp.busiq.core.domain.fare.FareRules;
import br.com.msp.busiq.infrastructure.persistence.entities.fare.FareRulesEntity;

public class FareRulesDtoMapper {
    public FareRulesEntity toEntity(FareRules fareRules) {
        return new FareRulesEntity(fareRules.fareId(),
                                   fareRules.routeId(),
                                   fareRules.originId(),
                                   fareRules.destinationId(),
                                   fareRules.containsId());
    }
}
