package br.com.msp.busiq.core.usecases.agency;

import br.com.msp.busiq.core.domain.Agency;
import br.com.msp.busiq.core.gateway.agency.AgencyGateway;

import java.util.List;

public class GetAgenciesInteractor implements GetAgenciesCase {
    private final AgencyGateway agencyGateway;

    public GetAgenciesInteractor(AgencyGateway agencyGateway) {
        this.agencyGateway = agencyGateway;
    }

    @Override
    public List<Agency> execute() {
        return agencyGateway.getAllAgencies();
    }
}
