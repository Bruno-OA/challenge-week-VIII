package br.com.ms.authandauto.apresentation.controller;

import br.com.ms.authandauto.application.dtos.MicroserviceDTO;
import br.com.ms.authandauto.application.dtos.UserMicroserviceRoleDTO;
import br.com.ms.authandauto.application.interfaces.IMicroserviceService;
import br.com.ms.authandauto.application.interfaces.IUserMicroserviceRoleService;
import br.com.ms.authandauto.domain.model.user.Reponse.UserRoleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserMicroserviceRoleController {
    private final IUserMicroserviceRoleService _userMicroserviceRoleService;
    @Autowired
    public UserMicroserviceRoleController(IUserMicroserviceRoleService userMicroserviceRoleService) {
        _userMicroserviceRoleService = userMicroserviceRoleService;
    }
    @GetMapping("/userMicroserviceRole/user/{id-user}/microservice/{id-microservice}")
    public ResponseEntity<UserMicroserviceRoleDTO> getUserMicroserviceRole(
            @PathVariable("id-user") Long userId,
            @PathVariable("id-microservice") Long microserviceId) {
        UserMicroserviceRoleDTO userMicroserviceRole = _userMicroserviceRoleService
                .findByUserAndMicroservice(userId, microserviceId);

        return ResponseEntity.ok(userMicroserviceRole);
    }

    @GetMapping("/userMicroserviceRole/{microserviceId}")
    public ResponseEntity<MicroserviceDTO> getMicroserviceByMicroserviceId(@PathVariable Long microserviceId) {
        MicroserviceDTO microserviceUsers = _userMicroserviceRoleService.findMicroserviceById(microserviceId);
        return ResponseEntity.ok(microserviceUsers);
    }

    @GetMapping("/userMicroserviceRole/{microserviceId}/users")
    public ResponseEntity<List<UserRoleResponse>> getUsersByMicroserviceId(@PathVariable Long microserviceId) {
        List<UserRoleResponse> userRoleResponses = _userMicroserviceRoleService.findUserByMicroserviceId(microserviceId);
        return ResponseEntity.ok(userRoleResponses);
    }
}
