package br.com.msp.busiq.config.routes;

import br.com.msp.busiq.core.gateway.routes.RoutesGateway;
import br.com.msp.busiq.core.usecases.routes.*;
import br.com.msp.busiq.infrastructure.gateway.routes.RoutesGatewayImpl;
import br.com.msp.busiq.infrastructure.mappers.routes.RoutesDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.repositories.RoutesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutesConfig {

    @Bean
    GetRoutesCase getRoutesCase(RoutesGateway routesGateway) {
        return new GetRoutesInteractor(routesGateway);
    }

    @Bean
    GetRouteByIdCase getRouteByIdCase(RoutesGateway routesGateway) {
        return new GetRouteByIdInteractor(routesGateway);
    }

    @Bean
    GetRoutesByAgencyIdCase getRoutesByAgencyIdCase(RoutesGateway routesGateway) {
        return new GetRoutesByAgencyIdInteractor(routesGateway);
    }

    @Bean
    GetRouteByRouteShortNameCase getRouteByRouteShortNameCase(RoutesGateway routesGateway) {
        return new GetRouteByRouteShortNameInteractor(routesGateway);
    }

    @Bean
    GetRoutesByContainsRouteLongNameCase getRoutesByContainsRouteLongNameCase(RoutesGateway routesGateway) {
        return new GetRoutesByContainsRouteLongNameInteractor(routesGateway);
    }

    @Bean
    GetRoutesByRouteColorCase getRoutesByRouteColorCase(RoutesGateway routesGateway) {
        return new GetRoutesByRouteColorInteractor(routesGateway);
    }

    @Bean
    RoutesGateway routesGateway(RoutesRepository routesRepository, RoutesDtoMapper routesDtoMapper) {
        return new RoutesGatewayImpl(routesRepository, routesDtoMapper);
    }

    @Bean
    RoutesDtoMapper routesDtoMapper() {
        return new RoutesDtoMapper();
    }
}
