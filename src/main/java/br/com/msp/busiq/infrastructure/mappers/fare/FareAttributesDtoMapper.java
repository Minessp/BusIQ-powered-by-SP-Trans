package br.com.msp.busiq.infrastructure.mappers.fare;

import br.com.msp.busiq.core.domain.fare.FareAttributes;
import br.com.msp.busiq.infrastructure.persistence.entities.fare.FareAttributesEntity;

public class FareAttributesDtoMapper {



    public FareAttributesEntity toEntity(FareAttributes fareAttributes) {
        return new FareAttributesEntity(
                fareAttributes.fareId(),
                fareAttributes.price(),
                fareAttributes.currencyType(),
                fareAttributes.paymentMethod(),
                fareAttributes.transfers(),
                fareAttributes.transferDuration()
        );
    }
}
