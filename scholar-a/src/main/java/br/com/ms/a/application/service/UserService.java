package br.com.ms.a.application.service;

import br.com.ms.a.domain.model.user.UserResponse;
import br.com.ms.a.domain.model.userMicroserviceRole.UserMicroserviceRole;
import br.com.ms.a.infra.FeignCliente.UserMicroserviceRoleFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserMicroserviceRoleFeign _userMicroserviceRoleFeign;

    @Autowired
    public UserService(UserMicroserviceRoleFeign userMicroserviceRoleFeign) {
        _userMicroserviceRoleFeign = userMicroserviceRoleFeign;
    }

    public UserResponse findUserById(Long userId, Long microserviceId) {
        UserMicroserviceRole userMicroserviceRole = _userMicroserviceRoleFeign.findById(userId, microserviceId);
        String userName = userMicroserviceRole.getUser().getName();
        String role = userMicroserviceRole.getRole();
        UserResponse userResponse = new UserResponse();
        userResponse.setName(userName);
        userResponse.setRole(role);
        return userResponse;
    }
}
