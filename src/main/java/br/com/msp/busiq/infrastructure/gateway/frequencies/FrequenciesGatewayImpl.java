package br.com.msp.busiq.infrastructure.gateway.frequencies;

import br.com.msp.busiq.core.domain.Frequencies;
import br.com.msp.busiq.core.gateway.frequencies.FrequenciesGateway;
import br.com.msp.busiq.infrastructure.mappers.frequencies.FrequenciesDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.repositories.FrequenciesRepository;

import java.util.List;

public class FrequenciesGatewayImpl implements FrequenciesGateway {
    private final FrequenciesRepository frequenciesRepository;
    private final FrequenciesDtoMapper frequenciesDtoMapper;

    public FrequenciesGatewayImpl(FrequenciesRepository frequenciesRepository, FrequenciesDtoMapper frequenciesDtoMapper) {
        this.frequenciesRepository = frequenciesRepository;
        this.frequenciesDtoMapper = frequenciesDtoMapper;
    }

    @Override
    public List<Frequencies> getAllFrequencies() {
        return frequenciesRepository.findAll().stream().map(frequenciesDtoMapper::toDomain).toList();
    }

    @Override
    public Frequencies getFrequencyById(String tripId) {
        if (tripId == null) {
            throw new IllegalArgumentException("Trip ID não pode ser nulo");
        }

        return frequenciesRepository.findById(tripId).map(frequenciesDtoMapper::toDomain).orElseThrow(
                () -> new IllegalArgumentException("Frequência não encontrada"));
    }
}
