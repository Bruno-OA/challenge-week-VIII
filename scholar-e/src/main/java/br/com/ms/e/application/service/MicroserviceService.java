package br.com.ms.e.application.service;

import br.com.ms.e.domain.model.enums.ErrorCodes;
import br.com.ms.e.domain.model.microsservice.Microservice;
import br.com.ms.e.domain.model.microsservice.MicroserviceResponse;
import br.com.ms.e.domain.model.user.UserResponse;
import br.com.ms.e.infra.FeignCliente.UserMicroserviceRoleFeign;
import br.com.ms.e.infra.constants.ErrorConstants;
import br.com.ms.e.infra.exceptions.ExceptionResponse;
import br.com.ms.e.infra.exceptions.UserMicroserviceRoleNotFoundException;
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
