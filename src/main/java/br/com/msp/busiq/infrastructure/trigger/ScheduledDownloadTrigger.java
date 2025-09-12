package br.com.msp.busiq.infrastructure.trigger;

import br.com.msp.busiq.core.usecases.DownloadGtfsCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledDownloadTrigger {
    private final DownloadGtfsCase downloadGtfsCase;

    public ScheduledDownloadTrigger(DownloadGtfsCase downloadGtfsCase) {
        this.downloadGtfsCase = downloadGtfsCase;
    }

    @Scheduled(cron = "0 0 12 * * ?", zone = "America/Sao_Paulo")
    public void execute() {
        downloadGtfsCase.execute();
    }
}
