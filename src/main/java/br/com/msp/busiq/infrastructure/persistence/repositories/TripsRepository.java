package br.com.msp.busiq.infrastructure.persistence.repositories;

import br.com.msp.busiq.infrastructure.persistence.entities.TripsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripsRepository extends JpaRepository<TripsEntity, String> {
}
