package br.com.msp.busiq.infrastructure.persistence.repositories;

import br.com.msp.busiq.infrastructure.persistence.entities.StopsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StopsRepository extends JpaRepository<StopsEntity, String> {
    List<StopsEntity> findAllByStopNameContainingIgnoreCase(String query);
}
