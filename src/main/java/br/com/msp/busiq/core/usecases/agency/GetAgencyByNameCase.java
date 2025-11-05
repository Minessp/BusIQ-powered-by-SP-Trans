package br.com.msp.busiq.core.usecases.agency;

import br.com.msp.busiq.core.domain.Agency;

public interface GetAgencyByNameCase {
    Agency execute(String agencyName);
}
