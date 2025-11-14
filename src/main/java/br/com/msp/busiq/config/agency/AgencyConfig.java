package br.com.msp.busiq.config.agency;

import br.com.msp.busiq.core.gateway.agency.AgencyGateway;
import br.com.msp.busiq.core.usecases.agency.*;
import br.com.msp.busiq.data.parser.TxtParser;
import br.com.msp.busiq.infrastructure.gateway.agency.AgencyGatewayImpl;
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
    GetAgencyByIdCase getAgencyByIdCase(AgencyGateway agencyGateway) {
        return new GetAgencyByIdInteractor(agencyGateway);
    }

    @Bean
    GetAgencyByNameCase getAgencyByNameCase(AgencyGateway agencyGateway) {
        return new GetAgencyByNameInteractor(agencyGateway);
    }

    @Bean
    AgencyGateway agencyGateway(AgencyRepository agencyRepository, AgencyDtoMapper agencyDtoMapper, TxtParser txtParser) {
        return new AgencyGatewayImpl(agencyRepository, agencyDtoMapper, txtParser);
    }

    @Bean
    AgencyDtoMapper agencyDtoMapper() {
        return new AgencyDtoMapper();
    }

    @Bean
    SaveAgencyDataCase saveAgencyDataCase(AgencyGateway agencyGateway) {
        return new SaveAgencyDataInteractor(agencyGateway);
    }
}
