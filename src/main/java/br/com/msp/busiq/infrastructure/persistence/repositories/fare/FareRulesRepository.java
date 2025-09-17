package br.com.msp.busiq.infrastructure.persistence.repositories.fare;

import br.com.msp.busiq.infrastructure.persistence.entities.fare.FareRulesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FareRulesRepository extends JpaRepository<FareRulesEntity, String> {
}
