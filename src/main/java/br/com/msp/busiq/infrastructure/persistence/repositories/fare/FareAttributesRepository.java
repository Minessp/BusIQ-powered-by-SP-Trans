package br.com.msp.busiq.infrastructure.persistence.repositories.fare;

import br.com.msp.busiq.infrastructure.persistence.entities.fare.FareAttributesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FareAttributesRepository extends JpaRepository<FareAttributesEntity, String> {
}
