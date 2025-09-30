package br.com.msp.busiq.config.agency;

import br.com.msp.busiq.core.gateway.agency.AgencyGateway;
import br.com.msp.busiq.core.usecases.agency.GetAgenciesCase;
import br.com.msp.busiq.core.usecases.agency.GetAgenciesInteractor;
import br.com.msp.busiq.infrastructure.gateway.gtfs.agency.AgencyGatewayImpl;
import br.com.msp.busiq.infrastructure.mappers.agency.AgencyDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.repositories.AgencyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgencyConfig {

    @Bean
    GetAgenciesCase getAgenciesCase(AgencyGateway agencyGateway) {
        return new GetAgenciesInteractor(agencyGateway);
    }

    @Bean
    AgencyGateway agencyGateway(AgencyRepository agencyRepository, AgencyDtoMapper agencyDTOMapper) {
        return new AgencyGatewayImpl(agencyRepository, agencyDTOMapper);
    }

    @Bean
    AgencyDtoMapper agencyDTOMapper() {
        return new AgencyDtoMapper();
    }
}
