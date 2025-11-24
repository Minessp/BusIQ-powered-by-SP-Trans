package br.com.msp.busiq.infrastructure.gateway.user;

import br.com.msp.busiq.core.domain.UserPrincipal;
import br.com.msp.busiq.core.gateway.user.UserGateway;
import br.com.msp.busiq.infrastructure.dtos.user.CreateUserRequest;
import br.com.msp.busiq.infrastructure.dtos.user.GetUserResponse;
import br.com.msp.busiq.infrastructure.dtos.user.UpdateUserRequest;
import br.com.msp.busiq.infrastructure.mappers.user.UserDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.entities.UserEntity;
import br.com.msp.busiq.infrastructure.persistence.repositories.UserRepository;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class UserGatewayImpl implements UserGateway {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final PasswordEncoder passwordEncoder;

    public UserGatewayImpl(UserRepository userRepository, UserDtoMapper userDtoMapper) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void createUser(CreateUserRequest request) {
        if(userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email já está em uso");
        }

        userRepository.save(userDtoMapper.createRequestToEntity(request));
    }

    @Override
    public void updateUser(UserPrincipal principal, UpdateUserRequest request) {
        if (!userRepository.existsById(principal.id())) {
            throw new AuthorizationDeniedException("Necessário ser cadastrado para acessar essa rota");
        }

        UserEntity user = userRepository.findById(principal.id()).orElseThrow(
                () -> new IllegalArgumentException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.currentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Credenciais inválidas");
        }

        if (request.email() != null && !request.email().isBlank()) {
            user.setEmail(request.email());
        }

        if (request.name() != null && !request.name().isBlank()) {
            user.setName(request.name());
        }

        if (request.password() != null && !request.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.password()));
        }

        userRepository.save(user);
    }

    @Override
    public void deleteUserById(String id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        userRepository.deleteById(id);
    }

    @Override
    public List<GetUserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(userDtoMapper::fromEntityToUserResponse).toList();
    }

    @Override
    public GetUserResponse getUserById(String id) {
        return userDtoMapper.fromEntityToUserResponse(userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Usuário não encontrado")));
    }
}
