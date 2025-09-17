package br.com.msp.busiq.infrastructure.persistence.repositories;

import br.com.msp.busiq.infrastructure.persistence.entities.ShapesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShapesRepository extends JpaRepository<ShapesEntity, String> {
}
