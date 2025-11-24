package br.com.msp.busiq.core.usecases.user;

import br.com.msp.busiq.infrastructure.dtos.user.GetUserResponse;

import java.util.List;

public interface GetUsersCase {
    List<GetUserResponse> execute();
}
