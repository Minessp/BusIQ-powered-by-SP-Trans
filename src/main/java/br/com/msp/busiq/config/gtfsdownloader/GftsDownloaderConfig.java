package br.com.msp.busiq.config.gtfsdownloader;

import br.com.msp.busiq.core.gateway.gtfs.GtfsGateway;
import br.com.msp.busiq.core.usecases.gtfs.DownloadGtfsCase;
import br.com.msp.busiq.core.usecases.gtfs.DownloadGtfsInteractor;
import br.com.msp.busiq.core.usecases.gtfs.ExtractGtfsCase;
import br.com.msp.busiq.core.usecases.gtfs.ExtractGtfsInteractor;
import br.com.msp.busiq.infrastructure.gateway.gtfs.GtfsGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GftsDownloaderConfig {

    @Bean
    GtfsGateway gtfsGateway() {
        return new GtfsGatewayImpl();
    }

    @Bean
    DownloadGtfsCase downloadGtfsCase(GtfsGateway gtfsGateway) {
        return new DownloadGtfsInteractor(gtfsGateway);
    }

    @Bean
    ExtractGtfsCase extractGtfsCase(GtfsGateway gtfsGateway) {
        return new ExtractGtfsInteractor(gtfsGateway);
    }
}
