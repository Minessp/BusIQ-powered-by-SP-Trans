package br.com.msp.busiq.infrastructure.trigger;

import br.com.msp.busiq.core.usecases.DownloadGtfsCase;
import br.com.msp.busiq.core.usecases.ExtractGtfsCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledDownloadTrigger {
    private final DownloadGtfsCase downloadGtfsCase;
    private final ExtractGtfsCase extractGtfsCase;

    public ScheduledDownloadTrigger(DownloadGtfsCase downloadGtfsCase, ExtractGtfsCase extractGtfsCase) {
        this.downloadGtfsCase = downloadGtfsCase;
        this.extractGtfsCase = extractGtfsCase;
    }

    @Scheduled(cron = "0 0 12 * * ?", zone = "America/Sao_Paulo")
    public void execute() {
        downloadGtfsCase.execute();
        extractGtfsCase.execute();
    }
}
