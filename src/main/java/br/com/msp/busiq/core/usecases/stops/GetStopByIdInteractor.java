package br.com.msp.busiq.core.usecases.stops;

import br.com.msp.busiq.core.domain.Stops;
import br.com.msp.busiq.core.gateway.stops.StopsGateway;

public class GetStopByIdInteractor implements GetStopByIdCase {
    private final StopsGateway stopsGateway;

    public GetStopByIdInteractor(StopsGateway stopsGateway) {
        this.stopsGateway = stopsGateway;
    }

    @Override
    public Stops execute(String stopId) {
        return stopsGateway.getStopById(stopId);
    }
}
