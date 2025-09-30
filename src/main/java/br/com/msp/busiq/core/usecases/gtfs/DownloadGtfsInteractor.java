package br.com.msp.busiq.core.usecases.gtfs;

import br.com.msp.busiq.core.gateway.gtfs.GtfsGateway;

public class DownloadGtfsInteractor implements DownloadGtfsCase {
    private final GtfsGateway gtfsGateway;

    public DownloadGtfsInteractor(GtfsGateway gtfsGateway) {
        this.gtfsGateway = gtfsGateway;
    }

    @Override
    public void execute() {
        gtfsGateway.download();
    }
}
