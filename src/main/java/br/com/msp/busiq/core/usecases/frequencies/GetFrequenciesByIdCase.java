package br.com.msp.busiq.core.usecases.frequencies;

import br.com.msp.busiq.core.domain.Frequencies;

import java.util.List;

public interface GetFrequenciesByIdCase {
    List<Frequencies> execute(String tripId);
}
