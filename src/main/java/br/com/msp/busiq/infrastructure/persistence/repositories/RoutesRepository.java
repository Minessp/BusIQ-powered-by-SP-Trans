package br.com.msp.busiq.infrastructure.persistence.repositories;

import br.com.msp.busiq.core.domain.Routes;
import br.com.msp.busiq.infrastructure.persistence.entities.RoutesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface RoutesRepository extends JpaRepository<RoutesEntity, String> {
    List<RoutesEntity> findAllByAgencyId(String agencyId);

    RoutesEntity findByRouteShortName(String routeShortName);

    List<RoutesEntity> findAllByRouteLongNameContainingIgnoreCase(String routeLongName);

    List<RoutesEntity> findAllByRouteColor(String routeColor);
}
