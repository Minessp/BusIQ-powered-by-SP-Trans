package br.com.msp.busiq.core.usecases.stoptimes;

import br.com.msp.busiq.core.domain.StopTimes;
import br.com.msp.busiq.core.gateway.stoptimes.StopTimesGateway;

public class GetStopTimeByTripIdAndStopIdInteractor implements GetStopTimeByTripIdAndStopIdCase {
    private final StopTimesGateway stopTimesGateway;

    public GetStopTimeByTripIdAndStopIdInteractor(StopTimesGateway stopTimesGateway) {
        this.stopTimesGateway = stopTimesGateway;
    }

    @Override
    public StopTimes execute(String tripId, String stopId) {
        return stopTimesGateway.getStopTimeByTripIdAndStopId(tripId, stopId);
    }
}
