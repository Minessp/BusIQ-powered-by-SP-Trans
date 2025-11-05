package br.com.msp.busiq.core.usecases.agency;

import br.com.msp.busiq.core.domain.Agency;
import br.com.msp.busiq.core.gateway.agency.AgencyGateway;

public class GetAgencyByIdInteractor implements GetAgencyByIdCase {
    private final AgencyGateway agencyGateway;

    public GetAgencyByIdInteractor(AgencyGateway agencyGateway) {
        this.agencyGateway = agencyGateway;
    }

    @Override
    public Agency execute(String agencyId) {
        return agencyGateway.getAgencyById(agencyId);
    }
}
