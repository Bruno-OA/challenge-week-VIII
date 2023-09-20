package br.com.ms.c.application.service;

import br.com.ms.c.domain.model.enums.ErrorCodes;
import br.com.ms.c.domain.model.microsservice.Microservice;
import br.com.ms.c.domain.model.microsservice.MicroserviceResponse;
import br.com.ms.c.domain.model.user.UserResponse;
import br.com.ms.c.infra.FeignCliente.UserMicroserviceRoleFeign;
import br.com.ms.c.infra.constants.ErrorConstants;
import br.com.ms.c.infra.exceptions.ExceptionResponse;
import br.com.ms.c.infra.exceptions.UserMicroserviceRoleNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MicroserviceService {
    private final UserMicroserviceRoleFeign _userMicroserviceRoleFeign;
    public MicroserviceService(UserMicroserviceRoleFeign userMicroserviceRoleFeign) {
        _userMicroserviceRoleFeign = userMicroserviceRoleFeign;
    }
    public MicroserviceResponse getMicroserviceByIdAndUsersPermissions(Long microserviceId) {
        try {
        Microservice microservice = _userMicroserviceRoleFeign.getMicroserviceByMicroserviceId(microserviceId);
        MicroserviceResponse response = new MicroserviceResponse();
        response.setId(microserviceId);
        response.setName(microservice.getName());
        List<UserResponse> users = _userMicroserviceRoleFeign.getUserByMicroserviceId(microserviceId);
        response.setUsers(users);
        return response;
        } catch (Exception ex) {
            throw new UserMicroserviceRoleNotFoundException(new ExceptionResponse(ErrorCodes.USER_MICROSERVICE_ROLE_NOT_FOUND,
                    ErrorConstants.USER_MICROSERVICE_ROLE_NOT_FOUND));
        }
    }
}
