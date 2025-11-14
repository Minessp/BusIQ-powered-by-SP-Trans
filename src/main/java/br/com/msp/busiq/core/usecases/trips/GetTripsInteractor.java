package br.com.msp.busiq.core.usecases.trips;

import br.com.msp.busiq.core.domain.Trips;
import br.com.msp.busiq.core.gateway.trips.TripsGateway;

import java.util.List;

public class GetTripsInteractor implements GetTripsCase {
    private final TripsGateway tripsGateway;

    public GetTripsInteractor(TripsGateway tripsGateway) {
        this.tripsGateway = tripsGateway;
    }

    @Override
    public List<Trips> execute() {
        return tripsGateway.getAllTrips();
    }
}
