package br.com.ms.authandauto.apresentation.controller;

import br.com.ms.authandauto.application.dtos.MicroserviceDTO;
import br.com.ms.authandauto.application.dtos.UserDTO;
import br.com.ms.authandauto.application.interfaces.IUserService;
import br.com.ms.authandauto.domain.enums.Role;
import br.com.ms.authandauto.domain.model.user.Reponse.UserMicroserviceResponse;
import br.com.ms.authandauto.domain.model.user.Reponse.UserResponse;
import br.com.ms.authandauto.domain.model.user.Requests.UserMicroserviceRequest;
import br.com.ms.authandauto.domain.model.user.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController()
@RequestMapping("api")
public class UserController {
    private final IUserService _userService;
    @Autowired
    public UserController(IUserService userService) {
        _userService = userService;
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        UserDTO userDTO = _userService.findById(id);
        UserResponse userResponse = new UserResponse(userDTO);
        return ResponseEntity.ok(userResponse);
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserMicroserviceResponse>> getUsersAndPermissions() {
        List<UserMicroserviceResponse> usersAndPermissions = _userService.getUsersAndPermissions();
        return ResponseEntity.ok(usersAndPermissions);
    }
    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserDTO userDTO)  {
        UserDTO user = _userService.createUser(userDTO);
        UserResponse userResponse = new UserResponse(user);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/users/{userId}/microservice/{microserviceId}")
    public ResponseEntity<Void> bindUserToMicroservice(
            @PathVariable Long userId,
            @PathVariable Long microserviceId,
            @RequestBody UserMicroserviceRequest request
    ) {
        _userService.bindUserToMicroservice(userId, microserviceId, request);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/users/{userId}/microservice/{microserviceId}/update-role")
    public ResponseEntity<Void> updateUserRoleInMicroservice(
            @PathVariable Long userId,
            @PathVariable Long microserviceId,
            @RequestParam Role newRole
    ) {
        try {
            _userService.updateUserRoleInMicroservice(userId, microserviceId, newRole);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
