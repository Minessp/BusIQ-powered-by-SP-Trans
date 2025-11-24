package br.com.msp.busiq.infrastructure.mappers.user;

import br.com.msp.busiq.infrastructure.dtos.user.CreateUserRequest;
import br.com.msp.busiq.infrastructure.dtos.user.GetUserResponse;
import br.com.msp.busiq.infrastructure.persistence.entities.RoleEntity;
import br.com.msp.busiq.infrastructure.persistence.entities.UserEntity;
import br.com.msp.busiq.infrastructure.persistence.repositories.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

public class UserDtoMapper {
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserDtoMapper(PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public UserEntity createRequestToEntity(CreateUserRequest request) {
        String roleName = request.role().equals("ADMIN") ? "ROLE_ADMIN" : "ROLE_USER";

        RoleEntity role = roleRepository.findById(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role não existe: " + roleName));

        return new UserEntity(request.name(), request.email(), passwordEncoder.encode(request.password()), role);
    }

    public GetUserResponse fromEntityToUserResponse(UserEntity userEntity) {
        return new GetUserResponse(userEntity.getId(), userEntity.getEmail(), new HashSet<>(userEntity.getRoles().
                stream().map(RoleEntity::getName).toList()));
    }
}
