package br.com.msp.busiq.core.gateway.fare;

import br.com.msp.busiq.core.domain.fare.FareAttributes;

import java.util.List;

public interface FareAttributesGateway {
    void saveFareAttributesData();

    List<FareAttributes> getFareAttributes();
}
