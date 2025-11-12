package br.com.msp.busiq.core.usecases.routes;

import br.com.msp.busiq.core.domain.Routes;
import br.com.msp.busiq.core.gateway.routes.RoutesGateway;

import java.util.List;

public class GetRoutesByAgencyIdInteractor implements GetRoutesByAgencyIdCase {
    private final RoutesGateway routesGateway;

    public GetRoutesByAgencyIdInteractor(RoutesGateway routesGateway) {
        this.routesGateway = routesGateway;
    }

    @Override
    public List<Routes> execute(String agencyId) {
        return routesGateway.getRoutesByAgencyId(agencyId);
    }
}
