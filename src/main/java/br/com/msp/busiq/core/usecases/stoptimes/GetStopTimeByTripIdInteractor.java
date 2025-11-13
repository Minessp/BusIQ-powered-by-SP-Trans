package br.com.msp.busiq.core.usecases.stoptimes;

import br.com.msp.busiq.core.domain.StopTimes;
import br.com.msp.busiq.core.gateway.stoptimes.StopTimesGateway;

import java.util.List;

public class GetStopTimeByTripIdInteractor implements GetStopTimeByTripIdCase {
    private final StopTimesGateway stopTimesGateway;

    public GetStopTimeByTripIdInteractor(StopTimesGateway stopTimesGateway) {
        this.stopTimesGateway = stopTimesGateway;
    }

    @Override
    public List<StopTimes> execute(String tripId) {
        return stopTimesGateway.getStopTimeById(tripId);
    }
}
