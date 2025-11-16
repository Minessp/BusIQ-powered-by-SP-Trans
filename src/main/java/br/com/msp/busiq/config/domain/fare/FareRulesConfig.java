package br.com.msp.busiq.config.domain.fare;

import br.com.msp.busiq.core.gateway.fare.FareRulesGateway;
import br.com.msp.busiq.core.usecases.fare.GetFareRulesCase;
import br.com.msp.busiq.core.usecases.fare.GetFareRulesInteractor;
import br.com.msp.busiq.core.usecases.fare.SaveFareRulesDataCase;
import br.com.msp.busiq.core.usecases.fare.SaveFareRulesDataInteractor;
import br.com.msp.busiq.data.parser.TxtParser;
import br.com.msp.busiq.infrastructure.gateway.fare.FareRulesGatewayImpl;
import br.com.msp.busiq.infrastructure.mappers.fare.FareRulesDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.repositories.fare.FareRulesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FareRulesConfig {

    @Bean
    SaveFareRulesDataCase saveFareRulesDataCase(FareRulesGateway fareRulesGateway) {
        return new SaveFareRulesDataInteractor(fareRulesGateway);
    }

    @Bean
    GetFareRulesCase getFareRulesCase(FareRulesGateway fareRulesGateway) {
        return new GetFareRulesInteractor(fareRulesGateway);
    }

    @Bean
    FareRulesGateway fareRulesGateway(FareRulesRepository fareRulesRepository, FareRulesDtoMapper fareRulesDtoMapper,
                                      TxtParser txtParser) {
        return new FareRulesGatewayImpl(fareRulesRepository, fareRulesDtoMapper, txtParser);
    }

    @Bean
    FareRulesDtoMapper fareRulesDtoMapper() {
        return new FareRulesDtoMapper();
    }
}
