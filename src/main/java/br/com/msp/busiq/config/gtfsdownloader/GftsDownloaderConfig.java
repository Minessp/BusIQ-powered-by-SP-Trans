package br.com.msp.busiq.config.gtfsdownloader;

import br.com.msp.busiq.core.gateway.GtfsDownloaderGateway;
import br.com.msp.busiq.core.usecases.DownloadGtfsCase;
import br.com.msp.busiq.core.usecases.DownloadGtfsInteractor;
import br.com.msp.busiq.infrastructure.gateway.GtfsDownloaderGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GftsDownloaderConfig {

    @Bean
    GtfsDownloaderGateway gtfsDownloaderGateway() {
        return new GtfsDownloaderGatewayImpl();
    }

    @Bean
    DownloadGtfsCase downloadGtfsCase(GtfsDownloaderGateway gtfsDownloaderGateway) {
        return new DownloadGtfsInteractor(gtfsDownloaderGateway);
    }
}
