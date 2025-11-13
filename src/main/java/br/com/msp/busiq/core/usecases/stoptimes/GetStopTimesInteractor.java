package br.com.msp.busiq.core.usecases.stoptimes;

import br.com.msp.busiq.core.domain.StopTimes;
import br.com.msp.busiq.core.gateway.stoptimes.StopTimesGateway;

import java.util.List;

public class GetStopTimesInteractor implements GetStopTimesCase {
    private final StopTimesGateway stopTimesGateway;

    public GetStopTimesInteractor(StopTimesGateway stopTimesGateway) {
        this.stopTimesGateway = stopTimesGateway;
    }

    @Override
    public List<StopTimes> execute() {
        return stopTimesGateway.getAllStopTimes();
    }
}
