package br.com.msp.busiq.core.usecases.stops;

import br.com.msp.busiq.core.gateway.stops.StopsGateway;

public class SaveStopsDataInteractor implements SaveStopsDataCase {
    private final StopsGateway stopsGateway;

    public SaveStopsDataInteractor(StopsGateway stopsGateway) {
        this.stopsGateway = stopsGateway;
    }

    @Override
    public void execute() {
        stopsGateway.saveStopsData();
    }
}
