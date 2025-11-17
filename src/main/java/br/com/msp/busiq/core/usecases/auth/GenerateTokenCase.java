package br.com.msp.busiq.core.usecases.auth;

import br.com.msp.busiq.infrastructure.persistence.entities.UserEntity;

public interface GenerateTokenCase {
    String execute(UserEntity user);
}
