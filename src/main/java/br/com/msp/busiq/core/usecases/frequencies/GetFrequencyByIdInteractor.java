package br.com.msp.busiq.core.usecases.frequencies;

import br.com.msp.busiq.core.domain.Frequencies;
import br.com.msp.busiq.core.gateway.frequencies.FrequenciesGateway;

public class GetFrequencyByIdInteractor implements GetFrequencyByIdCase {
    private final FrequenciesGateway frequenciesGateway;

    public GetFrequencyByIdInteractor(FrequenciesGateway frequenciesGateway) {
        this.frequenciesGateway = frequenciesGateway;
    }

    @Override
    public Frequencies execute(String tripId) {
        return frequenciesGateway.getFrequencyById(tripId);
    }
}
