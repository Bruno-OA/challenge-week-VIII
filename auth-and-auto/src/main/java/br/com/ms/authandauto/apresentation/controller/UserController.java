package br.com.ms.authandauto.apresentation.controller;

import br.com.ms.authandauto.application.dtos.UserDTO;
import br.com.ms.authandauto.application.interfaces.IMicroserviceService;
import br.com.ms.authandauto.application.interfaces.IUserService;
import br.com.ms.authandauto.domain.model.user.Reponse.UserMicroserviceResponse;
import br.com.ms.authandauto.domain.model.user.Requests.UserMicroserviceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController()
@RequestMapping("users")
public class UserController {
    private final IUserService _userService;
    @Autowired
    public UserController(IUserService userService) {
        _userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserMicroserviceResponse>> getUsersAndPermissions() {
        List<UserMicroserviceResponse> usersAndPermissions = _userService.getUsersAndPermissions();
        return ResponseEntity.ok(usersAndPermissions);
    }
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO)  {
        UserDTO user = _userService.createUser(userDTO);
        return ResponseEntity.ok(user);
    }
    @PutMapping(value = "/{id}")
    public void updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        _userService.saveUser(userDTO);
    }

    @PutMapping("/{userId}/microservice/{microserviceId}")
    public ResponseEntity<Void> bindUserToMicroservice(
            @PathVariable Long userId,
            @PathVariable Long microserviceId,
            @RequestBody UserMicroserviceRequest request
    ) {
        _userService.bindUserToMicroservice(userId, microserviceId, request);
        return ResponseEntity.ok().build();
    }

}
