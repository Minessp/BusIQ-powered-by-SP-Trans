package br.com.msp.busiq.infrastructure.mappers.trips;

import br.com.msp.busiq.core.domain.Trips;
import br.com.msp.busiq.infrastructure.dtos.TripsResponse;
import br.com.msp.busiq.infrastructure.persistence.entities.TripsEntity;

public class TripsDtoMapper {
    public Trips toDomain(TripsEntity tripsEntity) {
        return Trips.builder()
                .routeId(tripsEntity.getRouteId())
                .serviceId(tripsEntity.getServiceId())
                .tripId(tripsEntity.getTripId())
                .tripHeadsign(tripsEntity.getTripHeadsign())
                .directionId(tripsEntity.getDirectionId())
                .shapeId(tripsEntity.getShapeId())
                .build();
    }

    public TripsResponse toResponse(Trips trips) {
        return TripsResponse.builder()
                .routeId(trips.routeId())
                .serviceId(trips.serviceId())
                .tripId(trips.tripId())
                .tripHeadsign(trips.tripHeadsign())
                .directionId(trips.directionId())
                .shapeId(trips.shapeId())
                .build();
    }

    public TripsEntity toEntity(Trips trips) {
        return new TripsEntity(trips.routeId(),
                               trips.serviceId(),
                               trips.tripId(),
                               trips.tripHeadsign(),
                               trips.directionId(),
                               trips.shapeId());
    }
}
