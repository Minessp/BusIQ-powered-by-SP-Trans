package br.com.msp.busiq.core.usecases.trips;

import br.com.msp.busiq.core.domain.Trips;

public interface GetTripByTripIdCase {
    Trips execute(String tripId);
}
