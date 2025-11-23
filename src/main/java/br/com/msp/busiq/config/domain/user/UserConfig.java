package br.com.msp.busiq.config.domain.user;

import br.com.msp.busiq.core.gateway.user.UserGateway;
import br.com.msp.busiq.core.usecases.user.CreateUserCase;
import br.com.msp.busiq.core.usecases.user.CreateUserInteractor;
import br.com.msp.busiq.core.usecases.user.UpdateUserCase;
import br.com.msp.busiq.core.usecases.user.UpdateUserInteractor;
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
