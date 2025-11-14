package br.com.msp.busiq.core.usecases.trips;

import br.com.msp.busiq.core.domain.Trips;
import br.com.msp.busiq.core.gateway.trips.TripsGateway;

import java.util.List;

public class GetTripsByRouteIdInteractor implements GetTripsByRouteIdCase {
    private final TripsGateway tripsGateway;

    public GetTripsByRouteIdInteractor(TripsGateway tripsGateway) {
        this.tripsGateway = tripsGateway;
    }

    @Override
    public List<Trips> execute(String routeId) {
        return tripsGateway.getTripsByRouteId(routeId);
    }
}
