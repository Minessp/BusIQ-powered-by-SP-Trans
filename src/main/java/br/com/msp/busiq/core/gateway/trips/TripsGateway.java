package br.com.msp.busiq.core.gateway.trips;

import br.com.msp.busiq.core.domain.Trips;

import java.util.List;

public interface TripsGateway {
    List<Trips> getAllTrips();

    List<Trips> getTripsByRouteId(String tripId);

    Trips getTripByTripId(String tripId);

    List<Trips> getTripsByTripHeadsign(String query);

    void saveTripsData();
}
