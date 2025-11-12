package br.com.msp.busiq.core.usecases.frequencies;

import br.com.msp.busiq.core.domain.Frequencies;
import br.com.msp.busiq.core.gateway.frequencies.FrequenciesGateway;

import java.util.List;

public class GetFrequenciesByIdInteractor implements GetFrequenciesByIdCase {
    private final FrequenciesGateway frequenciesGateway;

    public GetFrequenciesByIdInteractor(FrequenciesGateway frequenciesGateway) {
        this.frequenciesGateway = frequenciesGateway;
    }

    @Override
    public List<Frequencies> execute(String tripId) {
        return frequenciesGateway.getFrequenciesById(tripId);
    }
}
