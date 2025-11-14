package br.com.msp.busiq.core.usecases.trips;

import br.com.msp.busiq.core.domain.Trips;
import br.com.msp.busiq.core.gateway.trips.TripsGateway;

import java.util.List;

public class GetTripsByTripHeadsignInteractor implements GetTripsByTripHeadsignCase {
    private final TripsGateway tripsGateway;

    public GetTripsByTripHeadsignInteractor(TripsGateway tripsGateway) {
        this.tripsGateway = tripsGateway;
    }

    @Override
    public List<Trips> execute(String query) {
        return tripsGateway.getTripsByTripHeadsign(query);
    }
}
