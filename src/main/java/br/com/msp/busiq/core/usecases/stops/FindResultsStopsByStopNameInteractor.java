package br.com.msp.busiq.core.usecases.stops;

import br.com.msp.busiq.core.domain.Stops;
import br.com.msp.busiq.core.gateway.stops.StopsGateway;

import java.util.List;

public class FindResultsStopsByStopNameInteractor implements FindResultsStopsByStopNameCase {
    private final StopsGateway stopsGateway;

    public FindResultsStopsByStopNameInteractor(StopsGateway stopsGateway) {
        this.stopsGateway = stopsGateway;
    }

    @Override
    public List<Stops> execute(String query) {
        return stopsGateway.findResultsStopsByStopName(query);
    }
}
