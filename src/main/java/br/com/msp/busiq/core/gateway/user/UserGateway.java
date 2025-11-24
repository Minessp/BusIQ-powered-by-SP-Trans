package br.com.msp.busiq.core.gateway.user;

import br.com.msp.busiq.core.domain.UserPrincipal;
import br.com.msp.busiq.infrastructure.dtos.user.CreateUserRequest;
import br.com.msp.busiq.infrastructure.dtos.user.GetUserResponse;
import br.com.msp.busiq.infrastructure.dtos.user.UpdateUserRequest;

import java.util.List;

public interface UserGateway {
    void createUser(CreateUserRequest request);

    void updateUser(UserPrincipal principal, UpdateUserRequest request);

    void deleteUserById(String id);

    List<GetUserResponse> getAllUsers();

    GetUserResponse getUserById(String id);
}
