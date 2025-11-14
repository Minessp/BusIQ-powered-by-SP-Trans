package br.com.msp.busiq.core.gateway.fare;

import br.com.msp.busiq.core.domain.fare.FareRules;

import java.util.List;

public interface FareRulesGateway {
    void saveFareRulesData();

    List<FareRules> getAllFareRules();
}
