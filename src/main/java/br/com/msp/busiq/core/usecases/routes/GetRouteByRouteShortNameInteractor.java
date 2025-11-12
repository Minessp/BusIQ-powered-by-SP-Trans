package br.com.msp.busiq.core.usecases.routes;

import br.com.msp.busiq.core.domain.Routes;
import br.com.msp.busiq.core.gateway.routes.RoutesGateway;

public class GetRouteByRouteShortNameInteractor implements GetRouteByRouteShortNameCase {
    private final RoutesGateway routesGateway;

    public GetRouteByRouteShortNameInteractor(RoutesGateway routesGateway) {
        this.routesGateway = routesGateway;
    }

    @Override
    public Routes execute(String routeShortName) {
        return routesGateway.getRouteByRouteShortName(routeShortName);
    }
}
