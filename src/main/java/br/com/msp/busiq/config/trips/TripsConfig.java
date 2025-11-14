package br.com.msp.busiq.config.trips;

import br.com.msp.busiq.core.gateway.trips.TripsGateway;
import br.com.msp.busiq.core.usecases.trips.*;
import br.com.msp.busiq.data.parser.TxtParser;
import br.com.msp.busiq.infrastructure.gateway.trips.TripsGatewayImpl;
import br.com.msp.busiq.infrastructure.mappers.trips.TripsDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.repositories.TripsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TripsConfig {

    @Bean
    GetTripsCase getTripsCase(TripsGateway tripsGateway) {
        return new GetTripsInteractor(tripsGateway);
    }

    @Bean
    GetTripsByRouteIdCase getTripByIdCase(TripsGateway tripsGateway) {
        return new GetTripsByRouteIdInteractor(tripsGateway);
    }

    @Bean
    GetTripByTripIdCase getTripByTripIdCase(TripsGateway tripsGateway) {
        return new GetTripByTripIdInteractor(tripsGateway);
    }

    @Bean GetTripsByTripHeadsignCase getTripsByTripHeadsignCase(TripsGateway tripsGateway) {
        return new GetTripsByTripHeadsignInteractor(tripsGateway);
    }

    @Bean
    TripsGateway tripsGateway(TripsRepository tripsRepository, TripsDtoMapper tripsDtoMapper, TxtParser txtParser) {
        return new TripsGatewayImpl(tripsRepository, tripsDtoMapper, txtParser);
    }

    @Bean
    TripsDtoMapper tripsDtoMapper() {
        return new TripsDtoMapper();
    }

    @Bean
    SaveTripsDataCase saveTripsDataCase(TripsGateway tripsGateway) {
        return new SaveTripsDataInteractor(tripsGateway);
    }
}
