package br.com.msp.busiq.infrastructure.gateway.gtfs.agency;

import br.com.msp.busiq.core.domain.Agency;
import br.com.msp.busiq.core.gateway.agency.AgencyGateway;
import br.com.msp.busiq.infrastructure.mappers.agency.AgencyDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.repositories.AgencyRepository;

import java.util.List;

public class AgencyGatewayImpl implements AgencyGateway {
    private final AgencyRepository agencyRepository;
    private final AgencyDtoMapper agencyDTOMapper;

    public AgencyGatewayImpl(AgencyRepository agencyRepository, AgencyDtoMapper agencyDTOMapper) {
        this.agencyRepository = agencyRepository;
        this.agencyDTOMapper = agencyDTOMapper;
    }

    @Override
    public List<Agency> getAllAgencies() {
        return agencyRepository.findAll().stream().map(agencyDTOMapper::toDomain).toList();
    }
}
