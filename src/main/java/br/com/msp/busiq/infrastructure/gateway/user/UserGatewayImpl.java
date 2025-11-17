package br.com.msp.busiq.infrastructure.gateway.user;

import br.com.msp.busiq.core.gateway.user.UserGateway;
import br.com.msp.busiq.infrastructure.dtos.user.CreateUserRequest;
import br.com.msp.busiq.infrastructure.mappers.user.UserDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.entities.UserEntity;
import br.com.msp.busiq.infrastructure.persistence.repositories.UserRepository;

public class UserGatewayImpl implements UserGateway {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    public UserGatewayImpl(UserRepository userRepository, UserDtoMapper userDtoMapper) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
    }

    @Override
    public UserEntity createUser(CreateUserRequest request) {
        return userRepository.save(userDtoMapper.createRequestToEntity(request));
    }
}
