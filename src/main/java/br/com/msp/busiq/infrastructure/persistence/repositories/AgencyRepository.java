package br.com.msp.busiq.infrastructure.persistence.repositories;

import br.com.msp.busiq.infrastructure.persistence.entities.AgencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgencyRepository extends JpaRepository<AgencyEntity, String> {

    Optional<AgencyEntity> findByAgencyName(String agencyName);
}
