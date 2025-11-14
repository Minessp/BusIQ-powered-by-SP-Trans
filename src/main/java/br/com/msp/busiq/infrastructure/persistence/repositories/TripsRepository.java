package br.com.msp.busiq.infrastructure.persistence.repositories;

import br.com.msp.busiq.core.domain.Trips;
import br.com.msp.busiq.infrastructure.persistence.entities.TripsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripsRepository extends JpaRepository<TripsEntity, String> {
    List<TripsEntity> findByRouteId(String routeId);

    List<TripsEntity> findByTripHeadsignContainingIgnoreCase(String tripHeadsign);
}
