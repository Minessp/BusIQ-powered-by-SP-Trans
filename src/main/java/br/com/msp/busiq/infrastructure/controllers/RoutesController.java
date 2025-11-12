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

    @GetMapping("/agencyId/{agencyId}")
    public List<RoutesResponse> getRoutesByAgencyId(@PathVariable String agencyId) {
        return getRoutesByAgencyId.execute(agencyId).stream().map(routesDtoMapper::toResponse).toList();
    }

    @GetMapping("/routeShortName/{routeShortName}")
    public RoutesResponse getRouteByRouteShortName(@PathVariable String routeShortName){
        return routesDtoMapper.toResponse(getRouteByRouteShortNameCase.execute(routeShortName));
    }

    @GetMapping("/routeLongName")
    public List<RoutesResponse> findResultsRoutesByRouteLongName(@RequestParam("q") String query) {
        return getRoutesByContainsRouteLongName.execute(query).stream().map(routesDtoMapper::toResponse).toList();
    }

    @GetMapping("/routeColor/{routeColor}")
    public List<RoutesResponse> getRoutesByRouteColor(@PathVariable String routeColor) {
        return getRoutesByRouteColorCase.execute(routeColor).stream().map(routesDtoMapper::toResponse).toList();
    }
}
