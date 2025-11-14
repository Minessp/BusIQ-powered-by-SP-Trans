package br.com.msp.busiq.core.usecases.trips;

import br.com.msp.busiq.core.domain.Trips;
import br.com.msp.busiq.core.gateway.trips.TripsGateway;

public class GetTripByTripIdInteractor implements GetTripByTripIdCase {
    private final TripsGateway tripsGateway;

    public GetTripByTripIdInteractor(TripsGateway tripsGateway) {
        this.tripsGateway = tripsGateway;
    }

    @Override
    public Trips execute(String tripId) {
        return tripsGateway.getTripByTripId(tripId);
    }
}
