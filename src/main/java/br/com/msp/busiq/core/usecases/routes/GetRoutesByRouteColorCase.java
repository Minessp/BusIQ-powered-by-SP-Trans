package br.com.msp.busiq.core.usecases.routes;

import br.com.msp.busiq.core.domain.Routes;

import java.util.List;

public interface GetRoutesByRouteColorCase {
    List<Routes> execute(String routeColor);
}
