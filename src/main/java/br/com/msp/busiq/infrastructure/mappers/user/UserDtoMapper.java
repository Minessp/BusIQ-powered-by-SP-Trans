package br.com.msp.busiq.infrastructure.mappers.user;

import br.com.msp.busiq.infrastructure.dtos.user.CreateUserRequest;
import br.com.msp.busiq.infrastructure.persistence.entities.RoleEntity;
import br.com.msp.busiq.infrastructure.persistence.entities.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDtoMapper {
    private final PasswordEncoder passwordEncoder;

    public UserDtoMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity createRequestToEntity(CreateUserRequest request) {
        return new UserEntity(request.name(), request.email(), passwordEncoder.encode(request.password()),
                request.role().equals("ADMIN") ? new RoleEntity("ROLE_ADMIN") : new RoleEntity("ROLE_USER"));
    }
}
