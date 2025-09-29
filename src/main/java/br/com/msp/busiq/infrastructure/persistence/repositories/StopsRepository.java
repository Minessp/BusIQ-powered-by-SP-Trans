package br.com.msp.busiq.infrastructure.persistence.repositories;

import br.com.msp.busiq.infrastructure.persistence.entities.StopsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StopsRepository extends JpaRepository<StopsEntity, String> {
}
