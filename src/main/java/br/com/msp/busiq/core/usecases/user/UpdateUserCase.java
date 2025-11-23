package br.com.msp.busiq.core.usecases.user;

import br.com.msp.busiq.core.domain.UserPrincipal;
import br.com.msp.busiq.infrastructure.dtos.user.UpdateUserRequest;

public interface UpdateUserCase {
    void execute(UserPrincipal principal, UpdateUserRequest request);
}
