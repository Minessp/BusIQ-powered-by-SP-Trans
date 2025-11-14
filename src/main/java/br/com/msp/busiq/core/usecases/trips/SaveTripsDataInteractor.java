package br.com.msp.busiq.core.usecases.trips;

import br.com.msp.busiq.core.gateway.trips.TripsGateway;

public class SaveTripsDataInteractor implements SaveTripsDataCase {
    private final TripsGateway tripsGateway;

    public SaveTripsDataInteractor(TripsGateway tripsGateway) {
        this.tripsGateway = tripsGateway;
    }

    @Override
    public void execute() {
        tripsGateway.saveTripsData();
    }
}
