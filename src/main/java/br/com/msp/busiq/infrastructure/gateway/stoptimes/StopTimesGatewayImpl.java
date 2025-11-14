package br.com.msp.busiq.infrastructure.gateway.stoptimes;

import br.com.msp.busiq.core.domain.StopTimes;
import br.com.msp.busiq.core.gateway.stoptimes.StopTimesGateway;
import br.com.msp.busiq.data.parser.TxtParser;
import br.com.msp.busiq.infrastructure.mappers.stoptimes.StopTimesDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.entities.StopTimesEntity;
import br.com.msp.busiq.infrastructure.persistence.repositories.StopTimesRepository;

import java.util.List;

public class StopTimesGatewayImpl implements StopTimesGateway {
    private final StopTimesRepository stopTimesRepository;
    private final StopTimesDtoMapper stopTimesDtoMapper;
    private final TxtParser txtParser;

    public StopTimesGatewayImpl(StopTimesRepository stopTimesRepository, StopTimesDtoMapper stopTimesDtoMapper, TxtParser txtParser) {
        this.stopTimesRepository = stopTimesRepository;
        this.stopTimesDtoMapper = stopTimesDtoMapper;
        this.txtParser = txtParser;
    }

    @Override
    public List<StopTimes> getAllStopTimes() {
        return stopTimesRepository.findAll().stream().map(stopTimesDtoMapper::toDomain).toList();
    }

    @Override
    public List<StopTimes> getStopTimeById(String tripId) {
        if (tripId == null) {
            throw new IllegalArgumentException("Trip ID não pode ser nulo");
        }

        return stopTimesRepository.findAllByTripId(tripId).stream().map(stopTimesDtoMapper::toDomain).toList();
    }

    @Override
    public StopTimes getStopTimeByTripIdAndStopId(String tripId, String stopId) {
        return stopTimesRepository.findByTripIdAndStopId(tripId, stopId).map(stopTimesDtoMapper::toDomain).orElseThrow(
                () -> new RuntimeException("StopTime não encontrado baseado nos parâmetros fornecidos")
        );
    }

    @Override
    public void saveStopTimesData() {
        List<StopTimesEntity> stopTimes = txtParser.toStopTimes().stream().map(stopTimesDtoMapper::toEntity).toList();
        stopTimesRepository.saveAll(stopTimes);
    }
}
