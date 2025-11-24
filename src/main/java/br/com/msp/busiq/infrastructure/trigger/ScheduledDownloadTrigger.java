package br.com.msp.busiq.infrastructure.trigger;

import br.com.msp.busiq.core.gateway.data.SaveDataGateway;
import br.com.msp.busiq.core.usecases.gtfs.DownloadGtfsCase;
import br.com.msp.busiq.core.usecases.gtfs.ExtractGtfsCase;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledDownloadTrigger {
    private final DownloadGtfsCase downloadGtfsCase;
    private final ExtractGtfsCase extractGtfsCase;
    private final SaveDataGateway saveDataGateway;

    public ScheduledDownloadTrigger(DownloadGtfsCase downloadGtfsCase, ExtractGtfsCase extractGtfsCase,
                                    SaveDataGateway saveDataGateway) {
        this.downloadGtfsCase = downloadGtfsCase;
        this.extractGtfsCase = extractGtfsCase;
        this.saveDataGateway = saveDataGateway;
    }

    @Scheduled(cron = "0 0 12 * * ?", zone = "America/Sao_Paulo")
    public void scheduledTask() {
        executeTask();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onStartup() {
        executeTask();
    }

    private void executeTask() {
        downloadGtfsCase.execute();
        extractGtfsCase.execute();
        saveDataGateway.saveAllData();
    }
}
