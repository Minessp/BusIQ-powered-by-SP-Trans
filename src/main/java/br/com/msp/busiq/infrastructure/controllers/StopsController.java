package br.com.msp.busiq.infrastructure.controllers;

import br.com.msp.busiq.core.usecases.stops.FindResultsStopsByStopNameCase;
import br.com.msp.busiq.core.usecases.stops.GetStopByIdCase;
import br.com.msp.busiq.core.usecases.stops.GetStopsCase;
import br.com.msp.busiq.infrastructure.dtos.StopsResponse;
import br.com.msp.busiq.infrastructure.mappers.stops.StopsDtoMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stops")
public class StopsController {
    private final GetStopsCase getStopsCase;
    private final GetStopByIdCase getStopByIdCase;
    private final FindResultsStopsByStopNameCase findResultsStopsByStopNameCase;
    private final StopsDtoMapper stopsDtoMapper;

    public StopsController(GetStopsCase getStopsCase, GetStopByIdCase getStopByIdCase,
                           FindResultsStopsByStopNameCase findResultsStopsByStopNameCase ,StopsDtoMapper stopsDtoMapper) {
        this.getStopsCase = getStopsCase;
        this.getStopByIdCase = getStopByIdCase;
        this.findResultsStopsByStopNameCase = findResultsStopsByStopNameCase;
        this.stopsDtoMapper = stopsDtoMapper;
    }

    @GetMapping
    public List<StopsResponse> getStops() {
        return getStopsCase.execute().stream().map(stopsDtoMapper::toResponse).toList();
    }

    @GetMapping("/{stopId}")
    public StopsResponse getStopById(@PathVariable String stopId) {
        return stopsDtoMapper.toResponse(getStopByIdCase.execute(stopId));
    }

    @GetMapping("/stopName")
    public List<StopsResponse> findResultsStopsByStopName(@RequestParam("q") String query) {
        return findResultsStopsByStopNameCase.execute(query).stream().map(stopsDtoMapper::toResponse).toList();
    }
}
