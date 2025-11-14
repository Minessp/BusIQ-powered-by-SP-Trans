package br.com.msp.busiq.core.usecases.frequencies;

import br.com.msp.busiq.core.gateway.frequencies.FrequenciesGateway;

public class SaveFrequenciesDataInteractor implements SaveFrequenciesDataCase {
    private final FrequenciesGateway frequenciesGateway;

    public SaveFrequenciesDataInteractor(FrequenciesGateway frequenciesGateway) {
        this.frequenciesGateway = frequenciesGateway;
    }

    @Override
    public void execute() {
        frequenciesGateway.saveFrequenciesData();
    }
}
