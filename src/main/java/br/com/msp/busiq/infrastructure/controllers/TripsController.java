package br.com.msp.busiq.infrastructure.controllers;

import br.com.msp.busiq.core.usecases.trips.GetTripsByRouteIdCase;
import br.com.msp.busiq.core.usecases.trips.GetTripByTripIdCase;
import br.com.msp.busiq.core.usecases.trips.GetTripsByTripHeadsignCase;
import br.com.msp.busiq.core.usecases.trips.GetTripsCase;
import br.com.msp.busiq.infrastructure.dtos.TripsResponse;
import br.com.msp.busiq.infrastructure.mappers.trips.TripsDtoMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripsController {
    private final GetTripsCase getTripsCase;
    private final GetTripsByRouteIdCase getTripsByRouteIdCase;
    private final GetTripByTripIdCase getTripByTripIdCase;
    private final GetTripsByTripHeadsignCase getTripsByTripHeadsignCase;
    private final TripsDtoMapper tripsDtoMapper;

    public TripsController(GetTripsCase getTripsCase, GetTripsByRouteIdCase getTripsByRouteIdCase,
                           GetTripByTripIdCase getTripByTripIdCase, GetTripsByTripHeadsignCase getTripsByTripHeadsignCase,
                           TripsDtoMapper tripsDtoMapper) {
        this.getTripsCase = getTripsCase;
        this.getTripsByRouteIdCase = getTripsByRouteIdCase;
        this.getTripByTripIdCase = getTripByTripIdCase;
        this.getTripsByTripHeadsignCase = getTripsByTripHeadsignCase;
        this.tripsDtoMapper = tripsDtoMapper;
    }

    @GetMapping
    public List<TripsResponse> getTrips() {
        return getTripsCase.execute().stream().map(tripsDtoMapper::toResponse).toList();
    }

    @GetMapping("/route-id/{route-id}")
    public List<TripsResponse> getTripsByRouteId(@PathVariable("route-id") String routeId) {
        return getTripsByRouteIdCase.execute(routeId).stream().map(tripsDtoMapper::toResponse).toList();
    }

    @GetMapping("/{trip-id}")
    public TripsResponse getTripByTripId(@PathVariable("trip-id") String tripId) {
        return tripsDtoMapper.toResponse(getTripByTripIdCase.execute(tripId));
    }

    @GetMapping("/trip-headsign")
    public List<TripsResponse> findResultsTripsByTripHeadsign(@RequestParam("q") String query) {
        return getTripsByTripHeadsignCase.execute(query).stream().map(tripsDtoMapper::toResponse).toList();
    }
}
