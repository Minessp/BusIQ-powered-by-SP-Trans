package br.com.msp.busiq.core.usecases.fare;

import br.com.msp.busiq.core.gateway.fare.FareRulesGateway;

public class SaveFareRulesDataInteractor implements SaveFareRulesDataCase {
    private final FareRulesGateway fareRulesGateway;

    public SaveFareRulesDataInteractor(FareRulesGateway fareRulesGateway) {
        this.fareRulesGateway = fareRulesGateway;
    }

    @Override
    public void execute() {
        fareRulesGateway.saveFareRulesData();
    }
}
