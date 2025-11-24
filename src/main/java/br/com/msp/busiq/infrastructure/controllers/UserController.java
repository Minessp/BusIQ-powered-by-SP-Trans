package br.com.msp.busiq.infrastructure.controllers;

import br.com.msp.busiq.core.domain.UserPrincipal;
import br.com.msp.busiq.core.usecases.user.*;
import br.com.msp.busiq.infrastructure.dtos.user.CreateUserRequest;
import br.com.msp.busiq.infrastructure.dtos.user.GetUserResponse;
import br.com.msp.busiq.infrastructure.dtos.user.UpdateUserRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final CreateUserCase createUserCase;
    private final UpdateUserCase updateUserCase;
    private final DeleteUserByIdCase deleteUserByIdCase;
    private final GetUsersCase getUsersCase;
    private final GetUserByIdCase getUserByIdCase;

    public UserController(CreateUserCase createUserCase, UpdateUserCase updateUserCase,
                          DeleteUserByIdCase deleteUserByIdCase, GetUsersCase getUsersCase,
                          GetUserByIdCase getUserByIdCase) {
        this.createUserCase = createUserCase;
        this.updateUserCase = updateUserCase;
        this.deleteUserByIdCase = deleteUserByIdCase;
        this.getUsersCase = getUsersCase;
        this.getUserByIdCase = getUserByIdCase;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetUserResponse> getAllUsers() {
        return getUsersCase.execute();
    }

    @GetMapping("/{user-id}")
    @ResponseStatus(HttpStatus.OK)
    public GetUserResponse getUserById(@PathVariable("user-id") String userId) {
        return getUserByIdCase.execute(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody CreateUserRequest request) {
        createUserCase.execute(request);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@AuthenticationPrincipal UserPrincipal principal,
                           @Valid @RequestBody UpdateUserRequest request) {
        updateUserCase.execute(principal, request);
    }

    @DeleteMapping("/{user-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable("user-id") String userId) {
        deleteUserByIdCase.execute(userId);
    }
}
