package br.com.msp.busiq.infrastructure.gateway.frequencies;

import br.com.msp.busiq.core.domain.Frequencies;
import br.com.msp.busiq.core.gateway.frequencies.FrequenciesGateway;
import br.com.msp.busiq.data.parser.TxtParser;
import br.com.msp.busiq.infrastructure.mappers.frequencies.FrequenciesDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.entities.FrequenciesEntity;
import br.com.msp.busiq.infrastructure.persistence.repositories.FrequenciesRepository;

import java.util.List;

public class FrequenciesGatewayImpl implements FrequenciesGateway {
    private final FrequenciesRepository frequenciesRepository;
    private final FrequenciesDtoMapper frequenciesDtoMapper;
    private final TxtParser txtParser;

    public FrequenciesGatewayImpl(FrequenciesRepository frequenciesRepository,
                                  FrequenciesDtoMapper frequenciesDtoMapper,
                                  TxtParser txtParser) {
        this.frequenciesRepository = frequenciesRepository;
        this.frequenciesDtoMapper = frequenciesDtoMapper;
        this.txtParser = txtParser;
    }

    @Override
    public List<Frequencies> getAllFrequencies() {
        return frequenciesRepository.findAll().stream().map(frequenciesDtoMapper::toDomain).toList();
    }

    @Override
    public List<Frequencies> getFrequenciesById(String tripId) {
        if (tripId == null) {
            throw new IllegalArgumentException("Trip ID não pode ser nulo");
        }

        return frequenciesRepository.findAllByTripId(tripId).stream().map(frequenciesDtoMapper::toDomain).toList();
    }

    @Override
    public void saveFrequenciesData() {
        List<FrequenciesEntity> frequencies = txtParser.toFrequencies().stream()
                .map(frequenciesDtoMapper::toEntity).toList();
        frequenciesRepository.saveAll(frequencies);
    }
}
