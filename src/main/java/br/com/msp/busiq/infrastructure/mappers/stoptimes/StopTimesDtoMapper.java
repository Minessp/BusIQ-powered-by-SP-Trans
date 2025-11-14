package br.com.msp.busiq.infrastructure.mappers.stoptimes;

import br.com.msp.busiq.core.domain.StopTimes;
import br.com.msp.busiq.infrastructure.dtos.StopTimesResponse;
import br.com.msp.busiq.infrastructure.persistence.entities.StopTimesEntity;

public class StopTimesDtoMapper {
    public StopTimes toDomain(StopTimesEntity stopTimesEntity) {
        return StopTimes.builder()
                .tripId(stopTimesEntity.getTripId())
                .arrivalTime(stopTimesEntity.getArrivalTime())
                .departureTime(stopTimesEntity.getDepartureTime())
                .stopId(stopTimesEntity.getStopId())
                .stopSequence(stopTimesEntity.getStopSequence())
                .build();
    }

    public StopTimesResponse toResponse(StopTimes stopTimes) {
        return StopTimesResponse.builder()
                .tripId(stopTimes.tripId())
                .arrivalTime(stopTimes.arrivalTime())
                .departureTime(stopTimes.departureTime())
                .stopId(stopTimes.stopId())
                .stopSequence(stopTimes.stopSequence())
                .build();
    }

    public StopTimesEntity toEntity(StopTimes stopTimes) {
        return new StopTimesEntity(stopTimes.tripId(),
                                   stopTimes.arrivalTime(),
                                   stopTimes.departureTime(),
                                   stopTimes.stopId(),
                                   stopTimes.stopSequence());
    }
}
