package br.com.msp.busiq.infrastructure.controllers;

import br.com.msp.busiq.core.usecases.routes.*;
import br.com.msp.busiq.infrastructure.dtos.RoutesResponse;
import br.com.msp.busiq.infrastructure.mappers.routes.RoutesDtoMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routes")
public class RoutesController {
    private final GetRoutesCase getRoutesCase;
    private final GetRouteByIdCase getRouteByIdCase;
    private final GetRoutesByAgencyIdCase getRoutesByAgencyId;
    private final GetRouteByRouteShortNameCase getRouteByRouteShortNameCase;
    private final GetRoutesByContainsRouteLongNameCase getRoutesByContainsRouteLongName;
    private final GetRoutesByRouteColorCase getRoutesByRouteColorCase;
    private final RoutesDtoMapper routesDtoMapper;

    public RoutesController(GetRoutesCase getRoutesCase, GetRouteByIdCase getRouteByIdCase,
                            GetRoutesByAgencyIdCase getRoutesByAgencyId,
                            GetRouteByRouteShortNameCase getRouteByRouteShortNameCase,
                            GetRoutesByContainsRouteLongNameCase getRoutesByContainsRouteLongName,
                            GetRoutesByRouteColorCase getRoutesByRouteColorCase,
                            RoutesDtoMapper routesDtoMapper) {
        this.getRoutesCase = getRoutesCase;
        this.getRouteByIdCase = getRouteByIdCase;
        this.getRoutesByAgencyId = getRoutesByAgencyId;
        this.getRouteByRouteShortNameCase = getRouteByRouteShortNameCase;
        this.getRoutesByContainsRouteLongName = getRoutesByContainsRouteLongName;
        this.getRoutesByRouteColorCase = getRoutesByRouteColorCase;
        this.routesDtoMapper = routesDtoMapper;
    }

    @GetMapping
    public List<RoutesResponse> getRoutes() {
        return getRoutesCase.execute().stream().map(routesDtoMapper::toResponse).toList();
    }

    @GetMapping("/id/{id}")
    public RoutesResponse getRoute(@PathVariable String id) {
        return routesDtoMapper.toResponse(getRouteByIdCase.execute(id));
    }

    @GetMapping("/agency-id/{agency-id}")
    public List<RoutesResponse> getRoutesByAgencyId(@PathVariable("agency-id") String agencyId) {
        return getRoutesByAgencyId.execute(agencyId).stream().map(routesDtoMapper::toResponse).toList();
    }

    @GetMapping("/route-short-name/{route-short-name}")
    public RoutesResponse getRouteByRouteShortName(@PathVariable("route-short-name") String routeShortName){
        return routesDtoMapper.toResponse(getRouteByRouteShortNameCase.execute(routeShortName));
    }

    @GetMapping("/route-long-name")
    public List<RoutesResponse> findResultsRoutesByRouteLongName(@RequestParam("q") String query) {
        return getRoutesByContainsRouteLongName.execute(query).stream().map(routesDtoMapper::toResponse).toList();
    }

    @GetMapping("/route-color/{route-color}")
    public List<RoutesResponse> getRoutesByRouteColor(@PathVariable("route-color") String routeColor) {
        return getRoutesByRouteColorCase.execute(routeColor).stream().map(routesDtoMapper::toResponse).toList();
    }
}
