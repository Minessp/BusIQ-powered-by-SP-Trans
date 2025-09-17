package br.com.msp.busiq.infrastructure.persistence.repositories;

import br.com.msp.busiq.infrastructure.persistence.entities.FrequenciesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrequenciesRepository extends JpaRepository<FrequenciesEntity, String> {
}
