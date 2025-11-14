package br.com.msp.busiq.core.usecases.trips;

import br.com.msp.busiq.core.domain.Trips;

import java.util.List;

public interface GetTripsByRouteIdCase {
    List<Trips> execute(String routeId);
}
