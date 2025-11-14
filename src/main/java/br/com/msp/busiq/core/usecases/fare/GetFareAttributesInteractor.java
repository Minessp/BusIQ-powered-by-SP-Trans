package br.com.msp.busiq.core.usecases.fare;

import br.com.msp.busiq.core.domain.fare.FareAttributes;
import br.com.msp.busiq.core.gateway.fare.FareAttributesGateway;

import java.util.List;

public class GetFareAttributesInteractor implements GetFareAttributesCase {
    private final FareAttributesGateway fareAttributesGateway;

    public GetFareAttributesInteractor(FareAttributesGateway fareAttributesGateway) {
        this.fareAttributesGateway = fareAttributesGateway;
    }

    @Override
    public List<FareAttributes> execute() {
        return fareAttributesGateway.getFareAttributes();
    }
}
