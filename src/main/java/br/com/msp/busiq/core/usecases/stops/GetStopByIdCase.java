package br.com.msp.busiq.core.usecases.stops;

import br.com.msp.busiq.core.domain.Stops;

public interface GetStopByIdCase {
    Stops execute(String stopId);
}
