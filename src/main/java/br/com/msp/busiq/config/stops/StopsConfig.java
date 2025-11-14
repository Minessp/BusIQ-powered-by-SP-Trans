package br.com.msp.busiq.config.stops;

import br.com.msp.busiq.core.gateway.stops.StopsGateway;
import br.com.msp.busiq.core.usecases.stops.*;
import br.com.msp.busiq.data.parser.TxtParser;
import br.com.msp.busiq.infrastructure.gateway.stops.StopsGatewayImpl;
import br.com.msp.busiq.infrastructure.mappers.stops.StopsDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.repositories.StopsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StopsConfig {

    @Bean
    GetStopsCase getStopsCase(StopsGateway stopsGateway) {
        return new GetStopsInteractor(stopsGateway);
    }

    @Bean
    GetStopByIdCase getStopByIdCase(StopsGateway stopsGateway) {
        return new GetStopByIdInteractor(stopsGateway);
    }

    @Bean
    FindResultsStopsByStopNameCase findResultsStopsByStopNameCase(StopsGateway stopsGateway) {
        return new FindResultsStopsByStopNameInteractor(stopsGateway);
    }

    @Bean
    StopsGateway stopsGateway(StopsRepository stopsRepository, StopsDtoMapper stopsDtoMapper, TxtParser txtParser) {
        return new StopsGatewayImpl(stopsRepository, stopsDtoMapper, txtParser);
    }

    @Bean
    StopsDtoMapper stopsDtoMapper() {
        return new StopsDtoMapper();
    }

    @Bean
    SaveStopsDataCase saveStopsDataCase(StopsGateway stopsGateway) {
        return new SaveStopsDataInteractor(stopsGateway);
    }
}
