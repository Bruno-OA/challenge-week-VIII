package br.com.ms.scholarb.application.service;

import br.com.ms.scholarb.domain.model.enums.ErrorCodes;
import br.com.ms.scholarb.infra.constants.ErrorConstants;
import br.com.ms.scholarb.infra.exceptions.ExceptionResponse;
import br.com.ms.scholarb.infra.exceptions.UserMicroserviceRoleNotFoundException;
import br.com.ms.scholarb.domain.model.userMicroserviceRole.UserMicroserviceRole;
import br.com.ms.scholarb.domain.model.user.UserResponse;
import br.com.ms.scholarb.infra.FeingClient.UserMicroserviceRoleFeign;
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
