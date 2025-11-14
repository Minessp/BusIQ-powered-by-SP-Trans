package br.com.msp.busiq.infrastructure.gateway.trips;

import br.com.msp.busiq.core.domain.Trips;
import br.com.msp.busiq.core.gateway.trips.TripsGateway;
import br.com.msp.busiq.infrastructure.mappers.trips.TripsDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.repositories.TripsRepository;

import java.util.List;

public class TripsGatewayImpl implements TripsGateway {
    private final TripsRepository tripsRepository;
    private final TripsDtoMapper tripsDtoMapper;

    public TripsGatewayImpl(TripsRepository tripsRepository, TripsDtoMapper tripsDtoMapper) {
        this.tripsRepository = tripsRepository;
        this.tripsDtoMapper = tripsDtoMapper;
    }

    @Override
    public List<Trips> getAllTrips() {
        return tripsRepository.findAll().stream().map(tripsDtoMapper::toDomain).toList();
    }

    @Override
    public List<Trips> getTripsByRouteId(String routeId) {
        if (routeId == null) {
            throw new IllegalArgumentException("Route ID não pode ser nulo");
        }

        return tripsRepository.findByRouteId(routeId).stream().map(tripsDtoMapper::toDomain).toList();
    }

    @Override
    public Trips getTripByTripId(String tripId) {
        return tripsRepository.findById(tripId).map(tripsDtoMapper::toDomain).orElseThrow(
                () -> new RuntimeException("Trip não encontrada para esse Trip ID")
        );
    }

    @Override
    public List<Trips> getTripsByTripHeadsign(String query) {
        return tripsRepository.findByTripHeadsignContainingIgnoreCase(query).stream()
                .map(tripsDtoMapper::toDomain).toList();
    }
}
