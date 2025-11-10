package br.com.msp.busiq.core.usecases.frequencies;

import br.com.msp.busiq.core.domain.Frequencies;

public interface GetFrequencyByIdCase {
    Frequencies execute(String tripId);
}
