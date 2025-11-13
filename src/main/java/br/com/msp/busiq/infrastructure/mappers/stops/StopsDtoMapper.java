package br.com.msp.busiq.infrastructure.mappers.stops;

import br.com.msp.busiq.core.domain.Stops;
import br.com.msp.busiq.infrastructure.dtos.StopsResponse;
import br.com.msp.busiq.infrastructure.persistence.entities.StopsEntity;

public class StopsDtoMapper {
    public Stops toDomain(StopsEntity stopsEntity) {
        return Stops.builder()
                .stopId(stopsEntity.getStopId())
                .stopName(stopsEntity.getStopName())
                .stopDesc(stopsEntity.getStopDesc())
                .stopLat(stopsEntity.getStopLat())
                .stopLon(stopsEntity.getStopLon())
                .build();
    }

    public StopsResponse toResponse(Stops stops) {
        return StopsResponse.builder()
                .stopId(stops.stopId())
                .stopName(stops.stopName())
                .stopDesc(stops.stopDesc())
                .stopLat(stops.stopLat())
                .stopLon(stops.stopLon())
                .build();
    }
}
