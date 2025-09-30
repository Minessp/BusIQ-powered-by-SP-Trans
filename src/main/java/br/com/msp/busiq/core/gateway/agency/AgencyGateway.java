package br.com.msp.busiq.core.gateway.agency;

import br.com.msp.busiq.core.domain.Agency;

import java.util.List;

public interface AgencyGateway {
    List<Agency> getAllAgencies();
}
