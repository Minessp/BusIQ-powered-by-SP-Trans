package br.com.msp.busiq.infrastructure.gateway.agency;

import br.com.msp.busiq.core.domain.Agency;
import br.com.msp.busiq.core.gateway.agency.AgencyGateway;
import br.com.msp.busiq.data.parser.TxtParser;
import br.com.msp.busiq.infrastructure.mappers.agency.AgencyDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.entities.AgencyEntity;
import br.com.msp.busiq.infrastructure.persistence.repositories.AgencyRepository;

import java.util.List;

public class AgencyGatewayImpl implements AgencyGateway {
    private final AgencyRepository agencyRepository;
    private final AgencyDtoMapper agencyDtoMapper;
    private final TxtParser txtParser;

    public AgencyGatewayImpl(AgencyRepository agencyRepository, AgencyDtoMapper agencyDTOMapper, TxtParser txtParser) {
        this.agencyRepository = agencyRepository;
        this.agencyDtoMapper = agencyDTOMapper;
        this.txtParser = txtParser;
    }

    @Override
    public List<Agency> getAllAgencies() {
        return agencyRepository.findAll().stream().map(agencyDtoMapper::toDomain).toList();
    }

    @Override
    public Agency getAgencyById(String agencyId) {
        if (agencyId == null) {
            throw new IllegalArgumentException("Agency ID não pode ser nulo");
        }

        return agencyRepository.findById(agencyId).map(agencyDtoMapper::toDomain)
                .orElseThrow(() -> new IllegalArgumentException("Agência não encontrada"));
    }

    @Override
    public Agency getAgencyByName(String agencyName) {
        return agencyRepository.findByAgencyName(agencyName).map(agencyDtoMapper::toDomain)
                .orElseThrow(() -> new IllegalArgumentException("Agência não encontrada"));
    }

    @Override
    public void saveAgencyData() {
        List<AgencyEntity> agencies = txtParser.toAgency().stream().map(agencyDtoMapper::toEntity).toList();
        agencyRepository.saveAll(agencies);
    }
}
