package br.com.msp.busiq.core.gateway.stops;

import br.com.msp.busiq.core.domain.Stops;

import java.util.List;

public interface StopsGateway {
    List<Stops> getAllStops();

    Stops getStopById(String stopId);

    List<Stops> findResultsStopsByStopName(String query);

    void saveStopsData();
}
