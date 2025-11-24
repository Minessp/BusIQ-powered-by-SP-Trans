package br.com.msp.busiq.infrastructure.gateway.frequencies;

import br.com.msp.busiq.core.domain.Frequencies;
import br.com.msp.busiq.core.gateway.frequencies.FrequenciesGateway;
import br.com.msp.busiq.data.parser.TxtParser;
import br.com.msp.busiq.infrastructure.mappers.frequencies.FrequenciesDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.entities.FrequenciesEntity;
import br.com.msp.busiq.infrastructure.persistence.repositories.FrequenciesRepository;
import br.com.msp.busiq.infrastructure.persistence.repositories.TripsRepository;

import java.util.List;

public class FrequenciesGatewayImpl implements FrequenciesGateway {
    private final FrequenciesRepository frequenciesRepository;
    private final FrequenciesDtoMapper frequenciesDtoMapper;
    private final TxtParser txtParser;
    private final TripsRepository tripRepository;

    public FrequenciesGatewayImpl(FrequenciesRepository frequenciesRepository,
                                  FrequenciesDtoMapper frequenciesDtoMapper,
                                  TxtParser txtParser, TripsRepository tripRepository) {
        this.frequenciesRepository = frequenciesRepository;
        this.frequenciesDtoMapper = frequenciesDtoMapper;
        this.txtParser = txtParser;
        this.tripRepository = tripRepository;
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

        List<FrequenciesEntity> validFrequencies = frequencies.stream()
                .filter(f -> tripRepository.existsById(f.getTripId()))
                .toList();

        frequenciesRepository.saveAll(validFrequencies);
    }
}
