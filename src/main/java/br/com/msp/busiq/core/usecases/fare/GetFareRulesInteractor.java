package br.com.msp.busiq.core.usecases.fare;

import br.com.msp.busiq.core.domain.fare.FareRules;
import br.com.msp.busiq.core.gateway.fare.FareRulesGateway;

import java.util.List;

public class GetFareRulesInteractor implements GetFareRulesCase {
    private final FareRulesGateway fareRulesGateway;

    public GetFareRulesInteractor(FareRulesGateway fareRulesGateway) {
        this.fareRulesGateway = fareRulesGateway;
    }

    @Override
    public List<FareRules> execute() {
        return fareRulesGateway.getAllFareRules();
    }
}
