package br.com.msp.busiq.core.usecases.user;

import br.com.msp.busiq.infrastructure.dtos.user.GetUserResponse;

public interface GetUserByIdCase {
    GetUserResponse execute(String id);
}
