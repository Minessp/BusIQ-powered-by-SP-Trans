package br.com.msp.busiq.infrastructure.controllers;

import br.com.msp.busiq.core.usecases.stoptimes.GetStopTimeByTripIdAndStopIdCase;
import br.com.msp.busiq.core.usecases.stoptimes.GetStopTimeByTripIdCase;
import br.com.msp.busiq.core.usecases.stoptimes.GetStopTimesCase;
import br.com.msp.busiq.infrastructure.dtos.StopTimesResponse;
import br.com.msp.busiq.infrastructure.mappers.stoptimes.StopTimesDtoMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stop-times")
public class StopTimesController {
    private final GetStopTimesCase getStopTimesCase;
    private final GetStopTimeByTripIdCase getStopTimeByTripIdCase;
    private final GetStopTimeByTripIdAndStopIdCase getStopTimeByTripIdAndStopIdCase;
    private final StopTimesDtoMapper stopTimesDtoMapper;

    public StopTimesController(GetStopTimesCase getStopTimesCase, GetStopTimeByTripIdCase getStopTimeByTripIdCase,
                               GetStopTimeByTripIdAndStopIdCase getStopTimeByTripIdAndStopIdCase,
                               StopTimesDtoMapper stopTimesDtoMapper) {
        this.getStopTimesCase = getStopTimesCase;
        this.getStopTimeByTripIdCase = getStopTimeByTripIdCase;
        this.getStopTimeByTripIdAndStopIdCase = getStopTimeByTripIdAndStopIdCase;
        this.stopTimesDtoMapper = stopTimesDtoMapper;
    }

    @GetMapping
    public List<StopTimesResponse> getStopTimes() {
        return getStopTimesCase.execute().stream().map(stopTimesDtoMapper::toResponse).toList();
    }

    @GetMapping("/trip-id/{trip-id}")
    public List<StopTimesResponse> getStopTimesByTripId(@PathVariable("trip-id") String tripId) {
        return getStopTimeByTripIdCase.execute(tripId).stream().map(stopTimesDtoMapper::toResponse).toList();
    }

    @GetMapping("/trip-id/{trip-id}/{stop-id}")
    public StopTimesResponse getStopTimeByTripIdAndStopId(@PathVariable("trip-id") String tripId,
                                                          @PathVariable("stop-id") String stopId) {
        return stopTimesDtoMapper.toResponse(getStopTimeByTripIdAndStopIdCase.execute(tripId, stopId));
    }
}