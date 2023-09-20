package br.com.ms.d.apresentation.controller;

import br.com.ms.d.application.service.UserService;
import br.com.ms.d.domain.model.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/scholar/")
public class UserController {
    private final UserService _userService;

    @Autowired
    public UserController(UserService userService) {
        _userService = userService;
    }

    @GetMapping(value = "{id-user}/microservice/{id-microservice}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable("id-user") Long userId,
                                                     @PathVariable("id-microservice") Long microserviceId) {
        UserResponse userResponse = _userService.findUserById(userId, microserviceId);
        return ResponseEntity.ok(userResponse);
    }
}
