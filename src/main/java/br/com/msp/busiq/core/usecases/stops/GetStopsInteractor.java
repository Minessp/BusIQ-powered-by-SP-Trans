package br.com.msp.busiq.core.usecases.stops;

import br.com.msp.busiq.core.domain.Stops;
import br.com.msp.busiq.core.gateway.stops.StopsGateway;

import java.util.List;

public class GetStopsInteractor implements GetStopsCase {
    private final StopsGateway stopsGateway;

    public GetStopsInteractor(StopsGateway stopsGateway) {
        this.stopsGateway = stopsGateway;
    }

    @Override
    public List<Stops> execute() {
        return stopsGateway.getAllStops();
    }
}
