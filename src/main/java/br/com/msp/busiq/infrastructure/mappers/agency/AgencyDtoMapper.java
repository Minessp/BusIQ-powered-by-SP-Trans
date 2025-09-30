package br.com.msp.busiq.infrastructure.mappers.agency;

import br.com.msp.busiq.core.domain.Agency;
import br.com.msp.busiq.infrastructure.dtos.AgencyResponse;
import br.com.msp.busiq.infrastructure.persistence.entities.AgencyEntity;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class AgencyDtoMapper {
    public Agency toDomain(AgencyEntity agencyEntity) {
        return Agency.builder()
                .agencyId(agencyEntity.getAgencyId())
                .agencyName(agencyEntity.getAgencyName())
                .agencyUrl(toUrl(agencyEntity.getAgencyUrl()))
                .agencyTimezone(agencyEntity.getAgencyTimezone())
                .agencyLang(agencyEntity.getAgencyLang())
                .build();
    }

    public URL toUrl(String url) {
        try {
           return URI.create(url).toURL();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("URL enviada em formato inválido");
        }
    }

    public AgencyResponse toResponse(Agency agency) {
        return AgencyResponse.builder()
                .agencyId(agency.agencyId())
                .agencyName(agency.agencyName())
                .agencyUrl(agency.agencyUrl())
                .agencyTimezone(agency.agencyTimezone())
                .agencyLang(agency.agencyLang())
                .build();
    }
}
