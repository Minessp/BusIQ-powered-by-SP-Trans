package br.com.msp.busiq.core.usecases.routes;

import br.com.msp.busiq.core.domain.Routes;

public interface GetRouteByRouteShortNameCase {
    Routes execute(String routeShortName);
}
