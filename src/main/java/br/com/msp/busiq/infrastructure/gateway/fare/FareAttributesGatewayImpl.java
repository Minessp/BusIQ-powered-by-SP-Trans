package br.com.msp.busiq.infrastructure.gateway.fare;

import br.com.msp.busiq.core.gateway.fare.FareAttributesGateway;
import br.com.msp.busiq.data.parser.TxtParser;
import br.com.msp.busiq.infrastructure.mappers.fare.FareAttributesDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.entities.fare.FareAttributesEntity;
import br.com.msp.busiq.infrastructure.persistence.repositories.fare.FareAttributesRepository;

import java.util.List;

public class FareAttributesGatewayImpl implements FareAttributesGateway {
    private final FareAttributesRepository fareAttributesRepository;
    private final FareAttributesDtoMapper fareAttributesDtoMapper;
    private final TxtParser txtParser;

    public FareAttributesGatewayImpl(FareAttributesRepository fareAttributesRepository,
                                     FareAttributesDtoMapper fareAttributesDtoMapper, TxtParser txtParser) {
        this.fareAttributesRepository = fareAttributesRepository;
        this.fareAttributesDtoMapper = fareAttributesDtoMapper;
        this.txtParser = txtParser;
    }

    @Override
    public void saveFareAttributesData() {
        List<FareAttributesEntity> fareAttributes = txtParser.toFareAttributes().stream()
                .map(fareAttributesDtoMapper::toEntity).toList();
        fareAttributesRepository.saveAll(fareAttributes);
    }
}
