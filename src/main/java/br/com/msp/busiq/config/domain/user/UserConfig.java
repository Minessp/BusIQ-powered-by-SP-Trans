package br.com.msp.busiq.config.domain.user;

import br.com.msp.busiq.core.gateway.user.UserGateway;
import br.com.msp.busiq.core.usecases.user.*;
import br.com.msp.busiq.infrastructure.dtos.user.UpdateUserRequest;
import br.com.msp.busiq.infrastructure.gateway.user.UserGatewayImpl;
import br.com.msp.busiq.infrastructure.mappers.user.UserDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.repositories.RoleRepository;
import br.com.msp.busiq.infrastructure.persistence.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfig {

    @Bean
    CreateUserCase createUserCase(UserGateway userGateway) {
        return new CreateUserInteractor(userGateway);
    }

    @Bean
    UpdateUserCase updateUserCase(UserGateway userGateway) {
        return new UpdateUserInteractor(userGateway);
    }

    @Bean
    DeleteUserByIdCase deleteUserByIdCase(UserGateway userGateway) {
        return new DeleteUserByIdInteractor(userGateway);
    }

    @Bean
    GetUsersCase getUsersCase(UserGateway userGateway) {
        return new GetUsersInteractor(userGateway);
    }

    @Bean
    GetUserByIdCase getUserByIdCase(UserGateway userGateway) {
        return new GetUserByIdInteractor(userGateway);
    }

    @Bean
    UserGateway userGateway(UserRepository userRepository, UserDtoMapper userDtoMapper) {
        return new UserGatewayImpl(userRepository, userDtoMapper);
    }

    @Bean
    UserDtoMapper userDtoMapper(PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        return new UserDtoMapper(passwordEncoder, roleRepository);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
