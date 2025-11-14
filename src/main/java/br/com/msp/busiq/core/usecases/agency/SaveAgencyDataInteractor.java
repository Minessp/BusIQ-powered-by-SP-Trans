package br.com.msp.busiq.core.usecases.agency;

import br.com.msp.busiq.core.gateway.agency.AgencyGateway;

public class SaveAgencyDataInteractor implements SaveAgencyDataCase {
    private final AgencyGateway agencyGateway;

    public SaveAgencyDataInteractor(AgencyGateway agencyGateway) {
        this.agencyGateway = agencyGateway;
    }

    @Override
    public void execute() {
        agencyGateway.saveAgencyData();
    }
}
