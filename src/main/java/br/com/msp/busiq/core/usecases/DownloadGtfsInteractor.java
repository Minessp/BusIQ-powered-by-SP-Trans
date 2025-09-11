package br.com.msp.busiq.core.usecases;

import br.com.msp.busiq.core.gateway.GtfsDownloaderGateway;

public class DownloadGtfsInteractor implements DownloadGtfsCase {
    private final GtfsDownloaderGateway gtfsDownloaderGateway;

    public DownloadGtfsInteractor(GtfsDownloaderGateway gtfsDownloaderGateway) {
        this.gtfsDownloaderGateway = gtfsDownloaderGateway;
    }

    @Override
    public void execute() {
        gtfsDownloaderGateway.execute();
    }
}
