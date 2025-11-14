package br.com.msp.busiq.core.usecases.routes;

import br.com.msp.busiq.core.gateway.routes.RoutesGateway;

public class SaveRoutesDataInteractor implements SaveRoutesDataCase {
    private final RoutesGateway routesGateway;

    public SaveRoutesDataInteractor(RoutesGateway routesGateway) {
        this.routesGateway = routesGateway;
    }

    @Override
    public void execute() {
        routesGateway.saveRoutesData();
    }
}
