package br.com.msp.busiq.core.usecases.stops;

import br.com.msp.busiq.core.domain.Stops;

import java.util.List;

public interface FindResultsStopsByStopNameCase {
    List<Stops> execute(String query);
}
