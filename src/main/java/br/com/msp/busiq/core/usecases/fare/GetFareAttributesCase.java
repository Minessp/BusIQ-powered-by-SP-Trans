package br.com.msp.busiq.core.usecases.fare;

import br.com.msp.busiq.core.domain.fare.FareAttributes;

import java.util.List;

public interface GetFareAttributesCase {
    List<FareAttributes> execute();
}
