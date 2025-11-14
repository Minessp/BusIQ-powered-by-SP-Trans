package br.com.msp.busiq.infrastructure.mappers.fare;

import br.com.msp.busiq.core.domain.fare.FareAttributes;
import br.com.msp.busiq.infrastructure.dtos.fare.FareAttributesResponse;
import br.com.msp.busiq.infrastructure.persistence.entities.fare.FareAttributesEntity;

public class FareAttributesDtoMapper {
    public FareAttributes toDomain(FareAttributesEntity fareAttributesEntity) {
        return FareAttributes.builder()
                .fareId(fareAttributesEntity.getFareId())
                .price(fareAttributesEntity.getPrice())
                .currencyType(fareAttributesEntity.getCurrencyType())
                .paymentMethod(fareAttributesEntity.getPaymentMethod())
                .transfers(fareAttributesEntity.getTransfers())
                .transferDuration(fareAttributesEntity.getTransferDuration())
                .build();
    }

    public FareAttributesResponse toResponse(FareAttributes fareAttributes) {
        return FareAttributesResponse.builder()
                .fareId(fareAttributes.fareId())
                .price(fareAttributes.price())
                .currencyType(fareAttributes.currencyType())
                .paymentMethod(fareAttributes.paymentMethod())
                .transfers(fareAttributes.transfers())
                .transferDuration(fareAttributes.transferDuration())
                .build();
    }

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
