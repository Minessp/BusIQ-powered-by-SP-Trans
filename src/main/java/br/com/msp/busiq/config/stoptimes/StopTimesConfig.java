package br.com.msp.busiq.config.stoptimes;

import br.com.msp.busiq.core.gateway.stoptimes.StopTimesGateway;
import br.com.msp.busiq.core.usecases.stoptimes.*;
import br.com.msp.busiq.infrastructure.gateway.stoptimes.StopTimesGatewayImpl;
import br.com.msp.busiq.infrastructure.mappers.stoptimes.StopTimesDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.repositories.StopTimesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StopTimesConfig {

    @Bean
    GetStopTimesCase getStopTimesCase(StopTimesGateway stopTimesGateway) {
        return new GetStopTimesInteractor(stopTimesGateway);
    }

    @Bean
    GetStopTimeByTripIdCase getStopTimeByTripIdCase(StopTimesGateway stopTimesGateway) {
        return new GetStopTimeByTripIdInteractor(stopTimesGateway);
    }

    @Bean
    GetStopTimeByTripIdAndStopIdCase getStopTimeByTripIdAndStopIdCase(StopTimesGateway stopTimesGateway) {
        return new GetStopTimeByTripIdAndStopIdInteractor(stopTimesGateway);
    }

    @Bean
    StopTimesGateway stopTimesGateway(StopTimesRepository stopTimesRepository, StopTimesDtoMapper stopTimesDtoMapper) {
        return new StopTimesGatewayImpl(stopTimesRepository, stopTimesDtoMapper);
    }

    @Bean
    StopTimesDtoMapper stopTimesDtoMapper() {
        return new StopTimesDtoMapper();
    }
}
