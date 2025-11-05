package br.com.msp.busiq.infrastructure.gateway.agency;

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

    @Override
    public Agency getAgencyById(String agencyId) {
        if (agencyId == null) {
            throw new IllegalArgumentException("Agency ID não pode ser nulo");
        }

        return agencyDTOMapper.toDomain(agencyRepository.findById(agencyId).orElseThrow(
                () -> new IllegalArgumentException("Agência não encontrada")));
    }

    @Override
    public Agency getAgencyByName(String agencyName) {
        return agencyDTOMapper.toDomain(agencyRepository.findByAgencyName(agencyName)
                .orElseThrow(
                        () -> new IllegalArgumentException("Agência não encontrada")));
    }
}
