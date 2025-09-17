package br.com.msp.busiq.infrastructure.persistence.repositories;

import br.com.msp.busiq.infrastructure.persistence.entities.StopTimesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StopTimesRepository extends JpaRepository<StopTimesEntity, String> {
}
