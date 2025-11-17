package br.com.msp.busiq.infrastructure.controllers;

import br.com.msp.busiq.core.usecases.user.CreateUserCase;
import br.com.msp.busiq.infrastructure.dtos.user.CreateUserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final CreateUserCase createUserCase;

    public UserController(CreateUserCase createUserCase) {
        this.createUserCase = createUserCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody CreateUserRequest request) {
        createUserCase.execute(request);
    }
}
