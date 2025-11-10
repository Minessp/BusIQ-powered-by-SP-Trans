package br.com.msp.busiq.core.usecases.frequencies;

import br.com.msp.busiq.core.domain.Frequencies;
import br.com.msp.busiq.core.gateway.frequencies.FrequenciesGateway;

import java.util.List;

public class GetFrequenciesInteractor implements GetFrequenciesCase {
    private final FrequenciesGateway frequenciesGateway;

    public GetFrequenciesInteractor(FrequenciesGateway frequenciesGateway) {
        this.frequenciesGateway = frequenciesGateway;
    }

    @Override
    public List<Frequencies> execute() {
        return frequenciesGateway.getAllFrequencies();
    }
}
