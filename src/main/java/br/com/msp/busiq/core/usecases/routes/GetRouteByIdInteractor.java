package br.com.msp.busiq.core.usecases.routes;

import br.com.msp.busiq.core.domain.Routes;
import br.com.msp.busiq.core.gateway.routes.RoutesGateway;

public class GetRouteByIdInteractor implements GetRouteByIdCase {
    private final RoutesGateway routesGateway;

    public GetRouteByIdInteractor(RoutesGateway routesGateway) {
        this.routesGateway = routesGateway;
    }

    @Override
    public Routes execute(String routeId) {
        return routesGateway.getRouteById(routeId);
    }
}
