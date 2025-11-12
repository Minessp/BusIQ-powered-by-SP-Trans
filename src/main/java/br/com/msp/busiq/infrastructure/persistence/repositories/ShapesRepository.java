package br.com.msp.busiq.infrastructure.persistence.repositories;

import br.com.msp.busiq.infrastructure.persistence.entities.ShapesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShapesRepository extends JpaRepository<ShapesEntity, String> {
    List<ShapesEntity> findAllByShapeId(String shapeId);

    Optional<ShapesEntity> findByShapeIdAndSequence(String shapeId, int sequence);
}
