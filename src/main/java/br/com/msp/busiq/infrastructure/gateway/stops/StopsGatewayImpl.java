package br.com.msp.busiq.infrastructure.gateway.stops;

import br.com.msp.busiq.core.domain.Stops;
import br.com.msp.busiq.core.gateway.stops.StopsGateway;
import br.com.msp.busiq.infrastructure.mappers.stops.StopsDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.repositories.StopsRepository;

import java.util.List;

public class StopsGatewayImpl implements StopsGateway {
    private final StopsRepository stopsRepository;
    private final StopsDtoMapper stopsDtoMapper;

    public StopsGatewayImpl(StopsRepository stopsRepository, StopsDtoMapper stopsDtoMapper) {
        this.stopsRepository = stopsRepository;
        this.stopsDtoMapper = stopsDtoMapper;
    }

    @Override
    public List<Stops> getAllStops() {
        return stopsRepository.findAll().stream().map(stopsDtoMapper::toDomain).toList();
    }

    @Override
    public Stops getStopById(String stopId) {
        if (stopId == null) {
            throw new IllegalArgumentException("Stop ID não pode ser nulo");
        }

        return stopsRepository.findById(stopId).map(stopsDtoMapper::toDomain).orElseThrow(
                () -> new IllegalArgumentException("Parada não encontrada")
        );
    }

    @Override
    public List<Stops> findResultsStopsByStopName(String query) {
        return stopsRepository.findAllByStopNameContainingIgnoreCase(query).stream()
                .map(stopsDtoMapper::toDomain).toList();
    }
}
