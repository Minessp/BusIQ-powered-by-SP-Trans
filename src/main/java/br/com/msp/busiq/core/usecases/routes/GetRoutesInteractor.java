package br.com.msp.busiq.core.usecases.routes;

import br.com.msp.busiq.core.domain.Routes;
import br.com.msp.busiq.core.gateway.routes.RoutesGateway;

import java.util.List;

public class GetRoutesInteractor implements GetRoutesCase {
    private final RoutesGateway routesGateway;

    public GetRoutesInteractor(RoutesGateway routesGateway) {
        this.routesGateway = routesGateway;
    }

    @Override
    public List<Routes> execute() {
        return routesGateway.getAllRoutes();
    }
}
