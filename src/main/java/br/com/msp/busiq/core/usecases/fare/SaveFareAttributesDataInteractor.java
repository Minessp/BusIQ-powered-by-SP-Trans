package br.com.msp.busiq.core.usecases.fare;

import br.com.msp.busiq.core.gateway.fare.FareAttributesGateway;

public class SaveFareAttributesDataInteractor implements SaveFareAttributesDataCase {
    private final FareAttributesGateway fareAttributesGateway;

    public SaveFareAttributesDataInteractor(FareAttributesGateway fareAttributesGateway) {
        this.fareAttributesGateway = fareAttributesGateway;
    }

    @Override
    public void execute() {
        fareAttributesGateway.saveFareAttributesData();
    }
}
