package br.com.msp.busiq.core.usecases.agency;

import br.com.msp.busiq.core.domain.Agency;
import br.com.msp.busiq.core.gateway.agency.AgencyGateway;

public class GetAgencyByNameInteractor implements GetAgencyByNameCase {
    private final AgencyGateway agencyGateway;

    public GetAgencyByNameInteractor(AgencyGateway agencyGateway) {
        this.agencyGateway = agencyGateway;
    }

    @Override
    public Agency execute(String agencyName) {
        return agencyGateway.getAgencyByName(agencyName);
    }
}
