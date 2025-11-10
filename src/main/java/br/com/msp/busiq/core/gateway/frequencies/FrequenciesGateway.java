package br.com.msp.busiq.core.gateway.frequencies;

import br.com.msp.busiq.core.domain.Frequencies;

import java.util.List;

public interface FrequenciesGateway {
    List<Frequencies> getAllFrequencies();

    Frequencies getFrequencyById(String tripId);
}
