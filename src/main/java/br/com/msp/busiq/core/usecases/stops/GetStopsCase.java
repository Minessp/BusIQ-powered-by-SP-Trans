package br.com.msp.busiq.core.usecases.stops;

import br.com.msp.busiq.core.domain.Stops;

import java.util.List;

public interface GetStopsCase {
    List<Stops> execute();
}
