package br.com.msp.busiq.infrastructure.mappers.routes;

import br.com.msp.busiq.core.domain.Routes;
import br.com.msp.busiq.infrastructure.dtos.RoutesResponse;
import br.com.msp.busiq.infrastructure.persistence.entities.RoutesEntity;

public class RoutesDtoMapper {
    public Routes toDomain(RoutesEntity routesEntity) {
        return Routes.builder()
                .routeId(routesEntity.getRouteId())
                .agencyId(routesEntity.getAgencyId())
                .routeShortName(routesEntity.getRouteShortName())
                .routeLongName(routesEntity.getRouteLongName())
                .routeType(routesEntity.getRouteType())
                .routeColor(routesEntity.getRouteColor())
                .routeTextColor(routesEntity.getRouteTextColor())
                .build();
    }

    public RoutesResponse toResponse(Routes routes) {
        return RoutesResponse.builder()
                .routeId(routes.routeId())
                .agencyId(routes.agencyId())
                .routeShortName(routes.routeShortName())
                .routeLongName(routes.routeLongName())
                .routeType(routes.routeType())
                .routeColor(routes.routeColor())
                .routeTextColor(routes.routeTextColor())
                .build();
    }

    public RoutesEntity toEntity(Routes routes) {
        return new RoutesEntity(routes.routeId(),
                                routes.agencyId(),
                                routes.routeShortName(),
                                routes.routeLongName(),
                                routes.routeType(),
                                routes.routeColor(),
                                routes.routeTextColor());
    }
}
