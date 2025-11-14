package br.com.msp.busiq.config.fare;

import br.com.msp.busiq.core.gateway.fare.FareAttributesGateway;
import br.com.msp.busiq.core.usecases.fare.GetFareAttributesCase;
import br.com.msp.busiq.core.usecases.fare.GetFareAttributesInteractor;
import br.com.msp.busiq.core.usecases.fare.SaveFareAttributesDataCase;
import br.com.msp.busiq.core.usecases.fare.SaveFareAttributesDataInteractor;
import br.com.msp.busiq.data.parser.TxtParser;
import br.com.msp.busiq.infrastructure.gateway.fare.FareAttributesGatewayImpl;
import br.com.msp.busiq.infrastructure.mappers.fare.FareAttributesDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.repositories.fare.FareAttributesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FareAttributesConfig {

    @Bean
    SaveFareAttributesDataCase saveFareAttributesDataCase(FareAttributesGateway fareAttributesGateway) {
        return new SaveFareAttributesDataInteractor(fareAttributesGateway);
    }

    @Bean
    GetFareAttributesCase getFareAttributesCase(FareAttributesGateway fareAttributesGateway) {
        return new GetFareAttributesInteractor(fareAttributesGateway);
    }

    @Bean
    FareAttributesGateway fareAttributesGateway(FareAttributesRepository fareAttributesRepository,
                                                FareAttributesDtoMapper fareAttributesDtoMapper, TxtParser txtParser) {
        return new FareAttributesGatewayImpl(fareAttributesRepository, fareAttributesDtoMapper, txtParser);
    }

    @Bean
    FareAttributesDtoMapper fareAttributesDtoMapper() {
        return new FareAttributesDtoMapper();
    }
}
