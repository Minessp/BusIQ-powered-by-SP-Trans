package br.com.msp.busiq.core.usecases.stoptimes;

import br.com.msp.busiq.core.gateway.stoptimes.StopTimesGateway;

public class SaveStopTimesDataInteractor implements SaveStopTimesDataCase {
    private final StopTimesGateway stopTimesGateway;

    public SaveStopTimesDataInteractor(StopTimesGateway stopTimesGateway) {
        this.stopTimesGateway = stopTimesGateway;
    }

    @Override
    public void execute() {
        stopTimesGateway.saveStopTimesData();
    }
}
