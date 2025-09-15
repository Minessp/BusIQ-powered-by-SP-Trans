package br.com.msp.busiq.core.usecases;

import br.com.msp.busiq.core.gateway.GtfsGateway;

public class ExtractGtfsInteractor implements ExtractGtfsCase {
    private final GtfsGateway gtfsGateway;

    public ExtractGtfsInteractor(GtfsGateway gtfsGateway) {
        this.gtfsGateway = gtfsGateway;
    }

    @Override
    public void execute() {
        gtfsGateway.extract();
    }
}
