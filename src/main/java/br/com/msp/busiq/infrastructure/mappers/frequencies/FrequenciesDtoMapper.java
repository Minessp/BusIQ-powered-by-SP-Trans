package br.com.msp.busiq.infrastructure.mappers.frequencies;

import br.com.msp.busiq.core.domain.Frequencies;
import br.com.msp.busiq.infrastructure.dtos.FrequenciesResponse;
import br.com.msp.busiq.infrastructure.persistence.entities.FrequenciesEntity;

public class FrequenciesDtoMapper {
    public Frequencies toDomain(FrequenciesEntity frequenciesEntity) {
        return Frequencies.builder()
                .tripId(frequenciesEntity.getTripId())
                .startTime(frequenciesEntity.getStartTime())
                .endTime(frequenciesEntity.getEndTime())
                .headwaySecs(frequenciesEntity.getHeadwaySecs())
                .build();
    }

    public FrequenciesResponse toResponse(Frequencies frequencies) {
        return FrequenciesResponse.builder()
                .tripId(frequencies.tripId())
                .startTime(frequencies.startTime())
                .endTime(frequencies.endTime())
                .headwaySecs(frequencies.headwaySecs())
                .build();
    }

    public FrequenciesEntity toEntity(Frequencies frequencies) {
        return new FrequenciesEntity(frequencies.tripId(),
                                     frequencies.startTime(),
                                     frequencies.endTime(),
                                     frequencies.headwaySecs());
    }
}
