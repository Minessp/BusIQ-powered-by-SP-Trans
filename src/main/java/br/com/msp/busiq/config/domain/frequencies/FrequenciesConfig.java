package br.com.msp.busiq.config.domain.frequencies;

import br.com.msp.busiq.core.gateway.frequencies.FrequenciesGateway;
import br.com.msp.busiq.core.usecases.frequencies.*;
import br.com.msp.busiq.data.parser.TxtParser;
import br.com.msp.busiq.infrastructure.gateway.frequencies.FrequenciesGatewayImpl;
import br.com.msp.busiq.infrastructure.mappers.frequencies.FrequenciesDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.repositories.FrequenciesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FrequenciesConfig {

    @Bean
    GetFrequenciesCase getFrequenciesCase(FrequenciesGateway frequenciesGateway) {
        return new GetFrequenciesInteractor(frequenciesGateway);
    }

    @Bean
    GetFrequenciesByIdCase getFrequencyByIdCase(FrequenciesGateway frequenciesGateway) {
        return new GetFrequenciesByIdInteractor(frequenciesGateway);
    }

    @Bean
    FrequenciesGateway frequenciesGateway(FrequenciesRepository frequenciesRepository,
                                          FrequenciesDtoMapper frequenciesDtoMapper,
                                          TxtParser txtParser) {
        return new FrequenciesGatewayImpl(frequenciesRepository, frequenciesDtoMapper, txtParser);
    }

    @Bean
    FrequenciesDtoMapper frequenciesDtoMapper() {
        return new FrequenciesDtoMapper();
    }

    @Bean
    SaveFrequenciesDataCase saveFrequenciesDataCase(FrequenciesGateway frequenciesGateway) {
        return new SaveFrequenciesDataInteractor(frequenciesGateway);
    }
}
