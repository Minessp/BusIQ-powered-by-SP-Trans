package br.com.msp.busiq.infrastructure.gateway.routes;

import br.com.msp.busiq.core.domain.Routes;
import br.com.msp.busiq.core.gateway.routes.RoutesGateway;
import br.com.msp.busiq.data.parser.TxtParser;
import br.com.msp.busiq.infrastructure.mappers.routes.RoutesDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.entities.RoutesEntity;
import br.com.msp.busiq.infrastructure.persistence.repositories.RoutesRepository;

import java.util.List;

public class RoutesGatewayImpl implements RoutesGateway {
    private final RoutesRepository routesRepository;
    private final RoutesDtoMapper routesDtoMapper;
    private final TxtParser txtParser;

    public RoutesGatewayImpl(RoutesRepository routesRepository, RoutesDtoMapper routesDtoMapper, TxtParser txtParser) {
        this.routesRepository = routesRepository;
        this.routesDtoMapper = routesDtoMapper;
        this.txtParser = txtParser;
    }

    @Override
    public List<Routes> getAllRoutes() {
        return routesRepository.findAll().stream().map(routesDtoMapper::toDomain).toList();
    }

    @Override
    public Routes getRouteById(String routeId) {
        if (routeId == null) {
            throw new IllegalArgumentException("Route ID não pode ser nulo");
        }

        return routesRepository.findById(routeId).map(routesDtoMapper::toDomain).orElseThrow(
                () -> new IllegalArgumentException("Rota não encontrada"));
    }

    @Override
    public List<Routes> getRoutesByAgencyId(String agencyId) {
        if (agencyId == null) {
            throw new IllegalArgumentException("Agency ID não pode ser nulo");
        }

        return routesRepository.findAllByAgencyId(agencyId).stream().map(routesDtoMapper::toDomain).toList();
    }

    @Override
    public Routes getRouteByRouteShortName(String routeShortName) {
        return routesDtoMapper.toDomain(routesRepository.findByRouteShortName(routeShortName));
    }

    @Override
    public List<Routes> getRoutesByContainsRouteLongName(String query) {
        return routesRepository.findAllByRouteLongNameContainingIgnoreCase(query).stream()
                .map(routesDtoMapper::toDomain).toList();
    }

    @Override
    public List<Routes> getRoutesByRouteColor(String routeColor) {
        return routesRepository.findAllByRouteColor(routeColor).stream().map(routesDtoMapper::toDomain).toList();
    }

    @Override
    public void saveRoutesData() {
        List<RoutesEntity> routes = txtParser.toRoutes().stream().map(routesDtoMapper::toEntity).toList();
        routesRepository.saveAll(routes);
    }
}
