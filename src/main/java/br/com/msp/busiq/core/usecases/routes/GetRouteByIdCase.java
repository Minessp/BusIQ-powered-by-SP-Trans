package br.com.msp.busiq.core.usecases.routes;

import br.com.msp.busiq.core.domain.Routes;

public interface GetRouteByIdCase {
    Routes execute(String routeId);
}
