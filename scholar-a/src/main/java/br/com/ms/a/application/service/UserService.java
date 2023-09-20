package br.com.ms.a.application.service;

import br.com.ms.a.domain.model.enums.ErrorCodes;
import br.com.ms.a.domain.model.user.UserResponse;
import br.com.ms.a.domain.model.userMicroserviceRole.UserMicroserviceRole;
import br.com.ms.a.infra.FeignCliente.UserMicroserviceRoleFeign;
import br.com.ms.a.infra.constants.ErrorConstants;
import br.com.ms.a.infra.exceptions.ExceptionResponse;
import br.com.ms.a.infra.exceptions.UserMicroserviceRoleNotFoundException;
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
        try {
            UserMicroserviceRole userMicroserviceRole = _userMicroserviceRoleFeign.findById(userId, microserviceId);
            String userName = userMicroserviceRole.getUser().getName();
            String role = userMicroserviceRole.getRole();
            UserResponse userResponse = new UserResponse();
            userResponse.setName(userName);
            userResponse.setRole(role);

            return userResponse;
        } catch (Exception ex) {
            throw new UserMicroserviceRoleNotFoundException(new ExceptionResponse(ErrorCodes.USER_MICROSERVICE_ROLE_NOT_FOUND,
                    ErrorConstants.USER_MICROSERVICE_ROLE_NOT_FOUND));
        }

    }
}
