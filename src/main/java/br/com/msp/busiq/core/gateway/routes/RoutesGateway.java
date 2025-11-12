package br.com.msp.busiq.core.gateway.routes;

import br.com.msp.busiq.core.domain.Routes;

import java.util.List;

public interface RoutesGateway {
    List<Routes> getAllRoutes();

    Routes getRouteById(String routeId);

    List<Routes> getRoutesByAgencyId(String agencyId);

    Routes getRouteByRouteShortName(String routeShortName);

    List<Routes> getRoutesByContainsRouteLongName(String query);

    List<Routes> getRoutesByRouteColor(String routeColor);
}
