package br.com.msp.busiq.infrastructure.persistence.repositories;

import br.com.msp.busiq.infrastructure.persistence.entities.StopTimesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StopTimesRepository extends JpaRepository<StopTimesEntity, String> {
    List<StopTimesEntity> findAllByTripId(String tripId);

    Optional<StopTimesEntity> findByTripIdAndStopId(String tripId, String stopId);
}
