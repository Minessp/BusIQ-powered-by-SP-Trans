package br.com.msp.busiq.core.usecases.fare;

import br.com.msp.busiq.core.domain.fare.FareRules;

import java.util.List;

public interface GetFareRulesCase {
    List<FareRules> execute();
}
