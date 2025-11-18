package br.com.msp.busiq.infrastructure.persistence.repositories;

import br.com.msp.busiq.infrastructure.persistence.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, String> {
}
